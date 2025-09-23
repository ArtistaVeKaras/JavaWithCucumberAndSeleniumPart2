package stepDefinitions;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class CustomFormatter implements ConcurrentEventListener {

        @Override
        public void setEventPublisher(EventPublisher publisher) {
            publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);
        }

        private void handleTestStepStarted(TestStepStarted event) {
            if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
                String stepText = testStep.getStep().getText();
                System.out.println("🔹 Executing: " + stepText);
        }
    }
}
