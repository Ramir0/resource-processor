package dev.amir.resourceprocessor.framework.output.rest.client;

import dev.amir.resourceprocessor.framework.output.rest.request.CompleteProcessRequest;
import dev.amir.resourceprocessor.framework.output.rest.request.GetResourceDataRequest;
import dev.amir.resourceprocessor.framework.output.rest.response.CompleteProcessResponse;
import dev.amir.resourceprocessor.framework.output.rest.response.GetResourceDataResponse;

public interface ResourceDataServiceRestClient {
    GetResourceDataResponse getResourceDataById(GetResourceDataRequest request);
    CompleteProcessResponse completeProcess(CompleteProcessRequest request);
}
