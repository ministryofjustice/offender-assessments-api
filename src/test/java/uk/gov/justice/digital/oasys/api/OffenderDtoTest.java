package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderSummary;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OffenderDtoTest {

    @Mock
    OffenderSummary offender;

    @BeforeEach
    public void setUp() {
        setupOffender();
    }

    @Test
    public void shouldBuildValidDto() {
        var dto = OffenderDto.from(offender);

        assertThat(dto.getOasysOffenderId()).isEqualTo(offender.getOffenderPk());
        assertThat(dto.getFamilyName()).isEqualTo(offender.getFamilyName());
        assertThat(dto.getForename1()).isEqualTo(offender.getForename1());
        assertThat(dto.getForename2()).isEqualTo(offender.getForename2());
        assertThat(dto.getForename3()).isEqualTo(offender.getForename3());
        assertThat(dto.getRiskToOthers()).isEqualTo(offender.getRiskToOthers());
        assertThat(dto.getRiskToSelf()).isEqualTo(offender.getRiskToSelf());
        assertThat(dto.getPnc()).isEqualTo(offender.getPnc());
        assertThat(dto.getCrn()).isEqualTo(offender.getCmsProbNumber());
        assertThat(dto.getNomisId()).isEqualTo(offender.getCmsPrisNumber());
        assertThat(dto.getLegacyCmsProbNumber()).isEqualTo(offender.getLegacyCmsProbNumber());
        assertThat(dto.getCroNumber()).isEqualTo(offender.getCroNumber());
        assertThat(dto.getBookingNumber()).isEqualTo(offender.getPrisonNumber());
        assertThat(dto.getMergePncNumber()).isEqualTo(offender.getMergePncNumber());
        assertThat(dto.isLimitedAccessOffender()).isTrue();
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = OffenderDto.from(null);

        assertThat(dto).isNull();
    }

    private void setupOffender() {
        when(offender.getOffenderPk()).thenReturn(12L);
        when(offender.getLimitedAccessOffender()).thenReturn("Y");
        when(offender.getPnc()).thenReturn("Any PNC");
        when(offender.getCmsProbNumber()).thenReturn("Any CMS Prob");
        when(offender.getCmsPrisNumber()).thenReturn("Any CMS Pris");
        when(offender.getLegacyCmsProbNumber()).thenReturn("Any Leg Prob");
        when(offender.getCroNumber()).thenReturn("Any cro");
        when(offender.getPrisonNumber()).thenReturn("Any prison number");
        when(offender.getMergePncNumber()).thenReturn("Any Merge PNC");
        when(offender.getFamilyName()).thenReturn("Family");
        when(offender.getForename1()).thenReturn("Any f1");
        when(offender.getForename2()).thenReturn("Any f2");
        when(offender.getForename3()).thenReturn("Any f3");
        when(offender.getRiskToSelf()).thenReturn("YES");
        when(offender.getRiskToOthers()).thenReturn("YES");
    }
}