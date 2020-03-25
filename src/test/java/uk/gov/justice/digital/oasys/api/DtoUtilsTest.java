package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DtoUtilsTest {

    @Mock
    RefElement refElement;

    @Test
    public void shouldToBooleanCapitalY() {
        Boolean value = DtoUtils.ynToBoolean("Y");
        assertThat(value).isTrue();
    }

    @Test
    public void shouldToBooleanCapitalN() {
        Boolean value = DtoUtils.ynToBoolean("N");
        assertThat(value).isFalse();
    }

    @Test
    public void shouldToBooleanLowerY() {
        Boolean value = DtoUtils.ynToBoolean("y");
        assertThat(value).isTrue();
    }

    @Test
    public void shouldToBooleanLowerN() {
        Boolean value = DtoUtils.ynToBoolean("n");
        assertThat(value).isFalse();
    }

    @Test
    public void shouldToBooleanNull() {
        Boolean value = DtoUtils.ynToBoolean(null);
        assertThat(value).isNull();
    }

    @Test
    public void shouldToBooleanOther() {
        Boolean value = DtoUtils.ynToBoolean("P");
        assertThat(value).isNull();
    }

    @Test
    public void shouldReturnRefElementDescriptionNull() {
        String result = DtoUtils.refElementDesc(null);
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnRefElementDescriptionValueNull() {
        when(refElement.getRefElementDesc()).thenReturn(null);
        String result = DtoUtils.refElementDesc(refElement);
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnRefElementDescriptionValue() {
        String value = "Hello";
        when(refElement.getRefElementDesc()).thenReturn(value);
        String result = DtoUtils.refElementDesc(refElement);
        assertThat(result).isEqualTo(value);
    }

    @Test
    public void shouldReturnRefElementCodeNull() {
        String result = DtoUtils.refElementCode(null);
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnRefElementCodeValueNull() {
        when(refElement.getRefElementCode()).thenReturn(null);
        String result = DtoUtils.refElementCode(refElement);
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnRefElementCodeValue() {
        String value = "Hello";
        when(refElement.getRefElementCode()).thenReturn(value);
        String result = DtoUtils.refElementCode(refElement);
        assertThat(result).isEqualTo(value);
    }
}