package uk.gov.justice.digital.oasys.jpa.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.QAssessment;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

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

    public Collection<Assessment> getAssessmentsForOffender(Long offenderId) {
        var query = getAssessmentsQueryForOffender(offenderId);
        return query.fetch();
    }

    private JPAQuery<Assessment> getAssessmentsQueryForOffender(Long offenderId){
        var query = queryFactory.selectFrom(QAssessment.assessment);
        return query.where(QAssessment.assessment.group.offenderPk.eq(offenderId));
    }

    private void filterQuery(JPAQuery<Assessment> query, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {

        if (Objects.nonNull(filterAssessmentStatus)) {
            query.where(QAssessment.assessment.assessmentStatus.equalsIgnoreCase(filterAssessmentStatus));
        }

        if (Objects.nonNull(filterAssessmentType)) {
            query.where(QAssessment.assessment.assessmentType.equalsIgnoreCase(filterAssessmentType));
        }

        if (Objects.nonNull(filterGroupStatus)) {
            query.where(QAssessment.assessment.group.historicStatus.equalsIgnoreCase(filterGroupStatus));
        }

        if (Objects.nonNull(filterVoided)) {
            if(Boolean.TRUE.equals(filterVoided)) {
                query.where(QAssessment.assessment.assessmentVoidedDate.isNotNull());
            } else {
                query.where(QAssessment.assessment.assessmentVoidedDate.isNull());
            }
        }

    }

}
