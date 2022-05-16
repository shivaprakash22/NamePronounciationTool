package com.wellsfargo.pronounciation.NamePronounciation.service.impl;

import java.util.Scanner;
import java.util.concurrent.Future;
import com.microsoft.cognitiveservices.speech.*;
import com.wellsfargo.pronounciation.NamePronounciation.model.PronounciationDetails;
import com.wellsfargo.pronounciation.NamePronounciation.service.NamePronounciation;
import org.springframework.stereotype.Service;

@Service("pronounciationService")
public class NamePronounciationServiceImpl implements NamePronounciation {

    @Override
    public void pronounceMessage(PronounciationDetails details){
        // Replace below with your own subscription key
        String speechSubscriptionKey = "1728281f9b6441cd96f5bdcbd7a9c77e";
        // Replace below with your own service region (e.g., "westus").
        String serviceRegion = "eastus";

        // Creates an instance of a speech synthesizer using speech configuration with specified
        // subscription key and service region and default speaker as audio output.
        try (SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
             SpeechSynthesizer synth = new SpeechSynthesizer(config)) {

            assert(config != null);
            assert(synth != null);

            int exitCode = 1;

            System.out.println("Type some text that you want to speak...");
            System.out.print("> ");
            String text = details.getName();

            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);
            assert(task != null);

            SpeechSynthesisResult result = task.get();
            assert(result != null);

            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                System.out.println("Speech synthesized to speaker for text [" + text + "]");
                exitCode = 0;
            }
            else if (result.getReason() == ResultReason.Canceled) {
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(result);
                System.out.println("CANCELED: Reason=" + cancellation.getReason());

                if (cancellation.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                    System.out.println("CANCELED: Did you update the subscription info?");
                }
            }

            System.exit(exitCode);
        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());

            assert(false);
            System.exit(1);
        }
    }

}

