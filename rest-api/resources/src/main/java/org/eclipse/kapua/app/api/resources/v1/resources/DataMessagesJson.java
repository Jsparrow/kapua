/*******************************************************************************
 * Copyright (c) 2018 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.resources.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.eclipse.kapua.app.api.resources.v1.resources.marker.JsonSerializationFixed;
import org.eclipse.kapua.app.api.resources.v1.resources.model.DateParam;
import org.eclipse.kapua.app.api.resources.v1.resources.model.MetricType;
import org.eclipse.kapua.app.api.resources.v1.resources.model.ScopeId;
import org.eclipse.kapua.app.api.resources.v1.resources.model.StorableEntityId;
import org.eclipse.kapua.app.api.resources.v1.resources.model.data.JsonDatastoreMessage;
import org.eclipse.kapua.app.api.resources.v1.resources.model.data.JsonKapuaDataMessage;
import org.eclipse.kapua.app.api.resources.v1.resources.model.data.JsonMessageListResult;
import org.eclipse.kapua.app.api.resources.v1.resources.model.data.JsonMessageQuery;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.message.device.data.KapuaDataMessage;
import org.eclipse.kapua.message.device.data.KapuaDataMessageFactory;
import org.eclipse.kapua.message.device.data.KapuaDataPayload;
import org.eclipse.kapua.model.type.ObjectValueConverter;
import org.eclipse.kapua.service.KapuaService;
import org.eclipse.kapua.service.datastore.DatastoreObjectFactory;
import org.eclipse.kapua.service.datastore.model.DatastoreMessage;
import org.eclipse.kapua.service.datastore.model.MessageListResult;
import org.eclipse.kapua.service.datastore.model.StorableId;
import org.eclipse.kapua.service.datastore.model.query.MessageQuery;
import org.eclipse.kapua.service.datastore.model.query.SortField;
import org.eclipse.kapua.service.datastore.model.query.XmlAdaptedSortField;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @see JsonSerializationFixed
 */
@Api(value = "Data Messages", authorizations = {@Authorization(value = "kapuaAccessToken")})
@Path("{scopeId}/data/messages")
public class DataMessagesJson extends AbstractKapuaResource implements JsonSerializationFixed {

    private static final KapuaLocator LOCATOR = KapuaLocator.getInstance();

    private static final KapuaDataMessageFactory KAPUA_DATA_MESSAGE_FACTORY = LOCATOR.getFactory(KapuaDataMessageFactory.class);

    private static final DatastoreObjectFactory DATASTORE_OBJECT_FACTORY = LOCATOR.getFactory(DatastoreObjectFactory.class);

    private static final DataMessages DATA_MESSAGES = new DataMessages();

    /**
     * Gets the {@link DatastoreMessage} list in the scope.
     *
     * @param scopeId        The {@link ScopeId} in which to search results.
     * @param clientId       The client id to filter results.
     * @param channel        The channel id to filter results. It allows '#' wildcard in last channel level.
     * @param strictChannel  Restrict the search only to this channel ignoring its children. Only meaningful if channel is set.
     * @param startDateParam The start date to filter the results. Must come before endDate parameter.
     * @param endDateParam   The end date to filter the results. Must come after startDate parameter
     * @param offset         The result set offset.
     * @param limit          The result set limit.
     * @return The {@link MessageListResult} of all the datastoreMessages associated to the current selected scope.
     * @throws Exception Whenever something bad happens. See specific {@link KapuaService} exceptions.
     * @since 1.0.0
     */
    @ApiOperation(nickname = "dataMessageSimpleQuery",
            value = "Gets the DatastoreMessage list in the scope",
            notes = "Returns the list of all the datastoreMessages associated to the current selected scope.",
            response = MessageListResult.class)
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public <V extends Comparable<V>> JsonMessageListResult simpleQueryJson(
            @ApiParam(value = "The ScopeId in which to search results", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The client id to filter results") @QueryParam("clientId") String clientId,
            @ApiParam(value = "The channel to filter results.") @QueryParam("channel") String channel,
            @ApiParam(value = "Restrict the search only to this channel ignoring its children. Only meaningful if channel is set.") @QueryParam("strictChannel") boolean strictChannel,
            @ApiParam(value = "The start date to filter the results. Must come before endDate parameter") @QueryParam("startDate") DateParam startDateParam,
            @ApiParam(value = "The end date to filter the results. Must come after startDate parameter") @QueryParam("endDate") DateParam endDateParam,
            @ApiParam(value = "The metric name to filter results") @QueryParam("metricName") String metricName,
            @ApiParam(value = "The metric type to filter results") @QueryParam("metricType") MetricType<V> metricType,
            @ApiParam(value = "The min metric value to filter results") @QueryParam("metricMin") String metricMinValue,
            @ApiParam(value = "The max metric value to filter results") @QueryParam("metricMax") String metricMaxValue,
            @ApiParam(value = "The result set offset", defaultValue = "0") @QueryParam("offset") @DefaultValue("0") int offset,
            @ApiParam(value = "The result set limit", defaultValue = "50") @QueryParam("limit") @DefaultValue("50") int limit) throws Exception {

        MessageListResult result = DATA_MESSAGES.simpleQuery(
                scopeId,
                clientId,
                channel,
                strictChannel,
                startDateParam,
                endDateParam,
                metricName,
                metricType,
                metricMinValue,
                metricMaxValue,
                offset,
                limit);

        List<JsonDatastoreMessage> jsonDatastoreMessages = new ArrayList<>();
        result.getItems().forEach(m -> jsonDatastoreMessages.add(new JsonDatastoreMessage(m)));
        JsonMessageListResult jsonResult = new JsonMessageListResult();
        jsonResult.addItems(jsonDatastoreMessages);
        jsonResult.setTotalCount(result.getTotalCount());
        jsonResult.setLimitExceeded(result.isLimitExceeded());
        return jsonResult;
    }

    /**
     * Stores a new Message under the account of the currently connected user.
     * In this case, the provided message will only be stored in the back-end
     * database and it will not be forwarded to the message broker.
     *
     * @param jsonKapuaDataMessage The {@link KapuaDataMessage } to be stored
     * @return an {@link StorableEntityId} object encapsulating the response from
     * the datastore
     * @throws Exception Whenever something bad happens. See specific
     *                   {@link KapuaService} exceptions.
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(nickname = "dataMessageStore",
            value = "Stores a new KapuaDataMessage",
            notes = "Stores a new KapuaDataMessage under the account of the currently connected user. In this case, the provided message will only be stored in the back-end database and it will not be forwarded to the message broker.",
            response = StorableId.class)
    public StorableEntityId storeMessageJson(
            @ApiParam(value = "The ScopeId in which to store the message", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The KapuaDataMessage to be stored") JsonKapuaDataMessage jsonKapuaDataMessage) throws Exception {

        KapuaDataMessage kapuaDataMessage = KAPUA_DATA_MESSAGE_FACTORY.newKapuaDataMessage();

        kapuaDataMessage.setId(jsonKapuaDataMessage.getId());
        kapuaDataMessage.setScopeId(scopeId);
        kapuaDataMessage.setDeviceId(jsonKapuaDataMessage.getDeviceId());
        kapuaDataMessage.setClientId(jsonKapuaDataMessage.getClientId());
        kapuaDataMessage.setReceivedOn(jsonKapuaDataMessage.getReceivedOn());
        kapuaDataMessage.setSentOn(jsonKapuaDataMessage.getSentOn());
        kapuaDataMessage.setCapturedOn(jsonKapuaDataMessage.getCapturedOn());
        kapuaDataMessage.setPosition(jsonKapuaDataMessage.getPosition());
        kapuaDataMessage.setChannel(jsonKapuaDataMessage.getChannel());

        KapuaDataPayload kapuaDataPayload = KAPUA_DATA_MESSAGE_FACTORY.newKapuaDataPayload();

        if (jsonKapuaDataMessage.getPayload() != null) {
            kapuaDataPayload.setBody(jsonKapuaDataMessage.getPayload().getBody());

            jsonKapuaDataMessage.getPayload().getMetrics().forEach(
                    jsonMetric -> {
                        String name = jsonMetric.getName();
                        Object value = ObjectValueConverter.fromString(jsonMetric.getValue(), jsonMetric.getValueType());

                        kapuaDataPayload.getMetrics().put(name, value);
                    });
        }
        kapuaDataMessage.setPayload(kapuaDataPayload);

        return DATA_MESSAGES.storeMessage(scopeId, kapuaDataMessage);
    }

    /**
     * Queries the results with the given {@link MessageQuery} parameter.
     *
     * @param scopeId The {@link ScopeId} in which to search results.
     * @param query   The {@link MessageQuery} to used to filter results.
     * @return The {@link MessageListResult} of all the result matching the given {@link MessageQuery} parameter.
     * @throws Exception Whenever something bad happens. See specific {@link KapuaService} exceptions.
     * @since 1.0.0
     */
    @POST
    @Path("_query")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(nickname = "dataMessageQuery",
            value = "Queries the DatastoreMessages",
            notes = "Queries the DatastoreMessages with the given DatastoreMessageQuery parameter returning all matching DatastoreMessages",
            response = MessageListResult.class)
    public JsonMessageListResult queryJson(
            @ApiParam(value = "The ScopeId in which to search results", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The DatastoreMessageQuery to use to filter results", required = true) JsonMessageQuery query) throws Exception {
        query.setScopeId(scopeId);

        MessageListResult result = DATA_MESSAGES.query(scopeId, convertQuery(query));

        List<JsonDatastoreMessage> jsonDatastoreMessages = new ArrayList<>();
        result.getItems().forEach(m -> jsonDatastoreMessages.add(new JsonDatastoreMessage(m)));

        JsonMessageListResult jsonResult = new JsonMessageListResult();
        jsonResult.addItems(jsonDatastoreMessages);
        jsonResult.setLimitExceeded(result.isLimitExceeded());
        jsonResult.setTotalCount(result.getTotalCount());
        return jsonResult;
    }

    /**
     * Returns the DatastoreMessage specified by the "datastoreMessageId" path parameter.
     *
     * @param datastoreMessageId The id of the requested DatastoreMessage.
     * @return The requested DatastoreMessage object.
     * @throws Exception Whenever something bad happens. See specific {@link KapuaService} exceptions.
     * @since 1.0.0
     */
    @GET
    @Path("{datastoreMessageId}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(nickname = "dataMessageFind",
            value = "Gets an DatastoreMessage",
            notes = "Gets the DatastoreMessage specified by the datastoreMessageId path parameter",
            response = DatastoreMessage.class)
    public JsonDatastoreMessage findJson(
            @ApiParam(value = "The ScopeId of the requested DatastoreMessage.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The id of the requested DatastoreMessage", required = true) @PathParam("datastoreMessageId") StorableEntityId datastoreMessageId) throws Exception {
        DatastoreMessage datastoreMessage = DATA_MESSAGES.find(scopeId, datastoreMessageId);

        JsonDatastoreMessage jsonDatastoreMessage = new JsonDatastoreMessage(datastoreMessage);

        return DATA_MESSAGES.returnNotNullEntity(jsonDatastoreMessage);
    }

    private MessageQuery convertQuery(JsonMessageQuery query) {
        MessageQuery messageQuery = DATASTORE_OBJECT_FACTORY.newDatastoreMessageQuery(query.getScopeId());
        messageQuery.setAskTotalCount(query.isAskTotalCount());
        messageQuery.setFetchAttributes(query.getFetchAttributes());
        messageQuery.setFetchStyle(query.getFetchStyle());
        messageQuery.setLimit(query.getLimit());
        messageQuery.setOffset(query.getOffset());
        messageQuery.setPredicate(query.getPredicate());

        List<SortField> sortFields = new ArrayList<>();
        if (query.getSortFields() != null) {
            for (XmlAdaptedSortField xmlAdaptedSortField : query.getSortFields()) {
                sortFields.add(SortField.of(xmlAdaptedSortField.getDirection(), xmlAdaptedSortField.getField()));
            }
        }
        messageQuery.setSortFields(sortFields);
        return messageQuery;
    }
}
