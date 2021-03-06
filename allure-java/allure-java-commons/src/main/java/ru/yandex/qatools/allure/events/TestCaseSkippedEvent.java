package ru.yandex.qatools.allure.events;

import org.apache.commons.lang3.exception.ExceptionUtils;
import ru.yandex.qatools.allure.model.Failure;
import ru.yandex.qatools.allure.model.Status;
import ru.yandex.qatools.allure.model.TestCaseResult;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 11.11.13
 */
public class TestCaseSkippedEvent extends AbstractTestCaseSkippedEvent {

    @Override
    public void process(TestCaseResult testCase) {
        testCase.setStatus(Status.SKIPPED);
        testCase.setFailure(getFailure());
    }

    private Failure getFailure() {
        return new Failure()
                .withMessage(ExceptionUtils.getMessage(getThrowable()))
                .withStackTrace(ExceptionUtils.getStackTrace(getThrowable()));
    }
}
