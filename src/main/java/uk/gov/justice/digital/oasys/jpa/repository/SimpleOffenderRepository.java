package uk.gov.justice.digital.oasys.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderSummary;
import uk.gov.justice.digital.oasys.jpa.entity.QOffenderSummary;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import javax.persistence.EntityManager;

import java.util.Optional;

@Repository
public class SimpleOffenderRepository {

    private JPAQueryFactory queryFactory;

    @Autowired
    public SimpleOffenderRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<OffenderSummary> getOffender(String identifierType, String identifier) {
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.fromString(identifierType);
        return getOffenderByIdentifier(offenderIdentifier, identifier);
    }

    private Optional<OffenderSummary> getOffenderByIdentifier(OffenderIdentifier identityType, String identity) {
        QOffenderSummary qOffender = QOffenderSummary.offenderSummary;

        var query = queryFactory.selectFrom(qOffender);

        switch (identityType) {
            case CRN:
                query.where(qOffender.cmsProbNumber.eq(identity));
                query.where(qOffender.deletedDate.isNull());
                break;
            case PNC:
                query.where(qOffender.pnc.eq(identity));
                query.where(qOffender.deletedDate.isNull());
                break;
            case NOMIS:
                query.where(qOffender.cmsPrisNumber.eq(identity));
                query.where(qOffender.deletedDate.isNull());
                break;
            case OASYS:
                query.where(qOffender.offenderPk.eq(Long.valueOf(identity)));
                break;
            case BOOKING:
                query.where(qOffender.prisonNumber.eq(identity));
                query.where(qOffender.deletedDate.isNull());
                break;
            default:
                return Optional.empty();
        }

        var result = query.fetch();
        if(result.size() > 1) {
            throw new ApplicationExceptions.DuplicateOffenderRecordException(String.format("Duplicate offender found for %s %s", identityType, identity), LogEvent.DUPLICATE_OFFENDER_FOUND );
        }
        return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

}
