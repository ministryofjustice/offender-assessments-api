package uk.gov.justice.digital.oasys.jpa.repository.simple;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.QAssessment;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static uk.gov.justice.digital.oasys.utils.LogEvent.GET_ASSESSMENT_NOT_FOUND;
import static uk.gov.justice.digital.oasys.utils.LogEvent.GET_LATEST_ASSESSMENT_NOT_FOUND;

@Repository
public class SimpleAssessmentRepository {

    private JPAQueryFactory queryFactory;

    @Autowired
    public SimpleAssessmentRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<Assessment> getAssessment(Long oasysSetId) {
        var query = queryFactory.selectFrom(QAssessment.assessment);
        query.where(QAssessment.assessment.oasysSetPk.eq(oasysSetId));
        return Optional.ofNullable(query.fetchFirst());
    }

    public Optional<Assessment> getLatestAssessment(Long offenderId, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {
        var query = getAssessmentsQueryForOffender(offenderId);
        filterQuery(query, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        var assessment = query.orderBy(QAssessment.assessment.dateCompleted.desc()).fetchFirst();
        return Optional.ofNullable(assessment);
    }

    public Collection<Assessment> getAssessmentsForOffender(Long offenderId, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {
        var query = getAssessmentsQueryForOffender(offenderId);
        filterQuery(query, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        return query.fetch();
    }

    private JPAQuery<Assessment> getAssessmentsQueryForOffender(Long offenderId){
        var query = queryFactory.selectFrom(QAssessment.assessment);
        return query.where(QAssessment.assessment.group.offenderPk.eq(offenderId));
    }

    private void filterQuery(JPAQuery<Assessment> query, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {

        if (Objects.nonNull(filterAssessmentStatus)) {
            query.where(QAssessment.assessment.assessmentStatus.eq(filterAssessmentStatus));
        }

        if (Objects.nonNull(filterAssessmentType)) {
            query.where(QAssessment.assessment.assessmentType.eq(filterAssessmentType));
        }

        if (Objects.nonNull(filterGroupStatus)) {
            query.where(QAssessment.assessment.group.historicStatus.eq(filterGroupStatus));
        }

        if (Objects.nonNull(filterVoided) && Boolean.TRUE.equals(filterVoided)) {
            query.where(QAssessment.assessment.assessmentVoidedDate.isNull());
        }

    }

}
