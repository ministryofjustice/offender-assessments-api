package uk.gov.justice.digital.oasys.jpa.repository.simple;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.simple.QSection;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import javax.persistence.EntityManager;

import java.util.Collection;

import static net.logstash.logback.argument.StructuredArguments.value;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Slf4j
@Repository
public class SimpleSectionRepository {

    private JPAQueryFactory queryFactory;

    @Autowired
    public SimpleSectionRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Section getSectionForAssessment(Long oasysSetId, String sectionCode) {
        var query = queryFactory.selectFrom(QSection.section);
        query.where(QSection.section.oasysSetPk.eq(oasysSetId));
        query.where(QSection.section.refSection.refSectionCode.eq(sectionCode));
        //Section shouldn't throw not found as we use it to calculate things, which if null we can ignore.
        log.warn("Section {} for OasysSetId {}, not found!", sectionCode, oasysSetId, value(EVENT, LogEvent.GET_SECTION_NOT_FOUND));
        return query.fetchFirst();
    }

    public Collection<Section> getSectionsForAssessment(Long oasysSetId, String... sectionCodes) {
        var query = queryFactory.selectFrom(QSection.section);
        query.where(QSection.section.oasysSetPk.eq(oasysSetId));
        query.where(QSection.section.refSection.refSectionCode.in(sectionCodes));
        return query.fetch();
    }
}
