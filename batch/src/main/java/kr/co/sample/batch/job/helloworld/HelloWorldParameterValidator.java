package kr.co.sample.batch.job.helloworld;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class HelloWorldParameterValidator implements JobParametersValidator {
    private static final Pattern REQUEST_DATE_PATTERN =
            Pattern.compile("^2[0-9]{3,}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$");

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        var params = parameters.getParameters();

        validateRequestDateTime(params);
    }

    private void validateRequestDateTime(Map<String, JobParameter> params)
            throws JobParametersInvalidException {
        if (Objects.isNull(params.get("requestDate"))
                || !REQUEST_DATE_PATTERN
                .matcher(params.get("requestDate").getValue().toString())
                .matches()) {
            throw new JobParametersInvalidException(
                    String.format("배치 시작 일자가 올바르지 않습니다. [RequestDate=%s]", params.get("requestDate")));
        }
    }
}