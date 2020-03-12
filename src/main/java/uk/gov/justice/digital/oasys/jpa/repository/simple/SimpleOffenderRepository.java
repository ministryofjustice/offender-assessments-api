package uk.gov.justice.digital.oasys.jpa.repository.simple;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.jpa.entity.simple.OffenderSummary;
import uk.gov.justice.digital.oasys.jpa.entity.simple.QOffenderSummary;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;

import javax.persistence.EntityManager;

import java.util.Optional;

import static uk.gov.justice.digital.oasys.utils.LogEvent.OFFENDER_NOT_FOUND;

@Repository
public class SimpleOffenderRepository {

    private JPAQueryFactory queryFactory;

    @Autowired
    public SimpleOffenderRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public OffenderSummary getOffender(String identifierType, String identifier) {
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.fromString(identifierType);
        return getOffenderByIdentifier(offenderIdentifier, identifier);
    }

    private OffenderSummary getOffenderByIdentifier(OffenderIdentifier identityType, String identity) {
        QOffenderSummary qOffender = QOffenderSummary.offenderSummary;

        var query = queryFactory.selectFrom(qOffender);

        switch (identityType) {
            case CRN:
                query.where(qOffender.cmsProbNumber.eq(identity));
                break;
            case PNC:
                query.where(qOffender.pnc.eq(identity));
                break;
            case NOMIS:
                query.where(qOffender.cmsPrisNumber.eq(identity));
                break;
            case OASYS:
                query.where(qOffender.offenderPk.eq(Long.valueOf(identity)));
                break;
            case BOOKING:
                query.where(qOffender.prisonNumber.eq(identity));
                break;
            default:
                throw throwNotFound(identityType,identity);
        }
        return Optional.ofNullable(query.fetchFirst()).orElseThrow(() -> throwNotFound(identityType,identity));
    }

    private RuntimeException throwNotFound(OffenderIdentifier identityType, String identity){
        return new ApplicationExceptions.EntityNotFoundException(String.format("Offender %s: %s, not found!", identityType, identity), OFFENDER_NOT_FOUND);
    }
}
