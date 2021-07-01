package com.coveo.pushapiclient;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Source {
    PlatformClient platformClient;

    public Source(String apiKey, String organizationId) {
        this.platformClient = new PlatformClient(apiKey, organizationId);
    }

    public HttpResponse<String> create(String name, SourceVisibility sourceVisibility) throws IOException, InterruptedException {
        return this.platformClient.createSource(name, sourceVisibility);
    }

    public HttpResponse<String> createOrUpdateSecurityIdentity(String securityProviderId, SecurityIdentityModel securityIdentityModel) throws IOException, InterruptedException {
        return this.platformClient.createOrUpdateSecurityIdentity(securityProviderId, securityIdentityModel);
    }

    public HttpResponse<String> createOrUpdateSecurityIdentityAlias(String securityProviderId, SecurityIdentityAliasModel securityIdentityAliasModel) throws IOException, InterruptedException {
        return this.platformClient.createOrUpdateSecurityIdentityAlias(securityProviderId, securityIdentityAliasModel);
    }

    public HttpResponse<String> deleteSecurityIdentity(String securityProviderId, SecurityIdentityDelete securityIdentityDelete) throws IOException, InterruptedException {
        return this.platformClient.deleteSecurityIdentity(securityProviderId, securityIdentityDelete);
    }

    public HttpResponse<String> deleteOldSecurityIdentities(String securityProviderId, SecurityIdentityDeleteOptions batchDelete) throws IOException, InterruptedException {
        return this.platformClient.deleteOldSecurityIdentities(securityProviderId, batchDelete);
    }

    public HttpResponse<String> manageSecurityIdentities(String securityProviderId, SecurityIdentityBatchConfig batchConfig) throws IOException, InterruptedException {
        return this.platformClient.manageSecurityIdentities(securityProviderId, batchConfig);
    }

    public HttpResponse<String> addOrUpdateDocument(String sourceId, DocumentBuilder docBuilder) throws IOException, InterruptedException {
        return this.platformClient.pushDocument(sourceId, docBuilder.marshal(), docBuilder.getDocument().uri);
    }

    public HttpResponse<String> batchUpdateDocuments(String sourceId, BatchUpdate batchUpdate) throws IOException, InterruptedException {
        HttpResponse<String> resFileContainer = this.platformClient.createFileContainer();
        FileContainer fileContainer = new Gson().fromJson(resFileContainer.body(), FileContainer.class);
        this.platformClient.uploadContentToFileContainer(sourceId, fileContainer, batchUpdate);
        return this.platformClient.pushFileContainerContent(sourceId, fileContainer);
    }
}
