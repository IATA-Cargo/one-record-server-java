package org.iata.config;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import org.iata.api.model.Error;
import org.iata.api.model.*;
import org.iata.cargo.model.*;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiEndPointsInfo() {
        return new OpenAPI()
                .info(new Info().title("ONE Record API Documentation")
                        .description("This OpenAPI specification describes the API endpoint structure of an ONE Record API implementation.<br/><br/>" +
                                "<b>Note:</b><br/>" +
                                "The included schemas can only be used to generate JSON-LD in the extended document form.<br/>" +
                                "However, to be fully ONE Record compliant, any ONE Record API (and any compliant ONE Record client)<br/>" +
                                "<b>MUST</b> support at least the expanded, compacted, and flattened document forms.<br/><br/>" +
                                "More information about the ONE Record specification are available on [IATA Github repository](https://github.com/IATA-Cargo/ONE-Record) and on the [ONE Record Developer Portal](https://onerecord.iata.org).")
                        .contact(new Contact().name("IATA").url("https://onerecord.iata.org/").email("onerecord@iata.org"))
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                        .version("2.0.0-dev"))
                .servers(new ArrayList<Server>(Arrays.asList(new Server().url("https://1r.example.com"))))
                //.components(new Components().addSchemas("Piece", ModelConverters.getInstance().readAllAsResolvedSchema(Piece.class).schema))

                ;
    }

    @Bean
    public OpenApiCustomiser sortSchemasAlphabetically() {
        return openApi -> {
            Map<String, Schema> schemas = openApi.getComponents().getSchemas();
            openApi.getComponents().setSchemas(new TreeMap<>(schemas));
        };
    }

    @Bean
    public OpenApiCustomiser sortPathsAndTagsAlphabetically() {
        return openApi -> {
            Map<String, PathItem> paths = openApi.getPaths();
            Paths sortedPaths = new Paths();
            //HashMap<String, PathItem> pathsTree = new HashMap<String, PathItem>(paths);

            HashMap<String, PathItem> sortedTree = new HashMap<String, PathItem>(paths);
            Set<Map.Entry<String, PathItem>> pathItems = sortedTree.entrySet();

            Map<String, Map.Entry<String, PathItem>> distinctTagMap = new TreeMap<String, Map.Entry<String, PathItem>>();
            for (Map.Entry<String, PathItem> entry : pathItems) {
                PathItem pathItem = entry.getValue();
                io.swagger.v3.oas.models.Operation getOp = pathItem.getGet();
                if (getOp != null) {
                    String tag = getOp.getTags().get(0);
                    if (!distinctTagMap.containsKey(tag)) {
                        distinctTagMap.put(tag, entry);
                    }
                }
                io.swagger.v3.oas.models.Operation postOp = pathItem.getPost();
                if (postOp != null) {
                    String tag1 = postOp.getTags().get(0);
                    if (!distinctTagMap.containsKey(tag1)) {
                        distinctTagMap.put(tag1, entry);
                    }
                }

                io.swagger.v3.oas.models.Operation putOp = pathItem.getPut();
                if (putOp != null) {
                    String tag2 = putOp.getTags().get(0);
                    if (!distinctTagMap.containsKey(tag2)) {
                        distinctTagMap.put(tag2, entry);
                    }
                }
            }

            LinkedHashMap<String, PathItem> customOrderMap = new LinkedHashMap<String, PathItem>();
            for (Map.Entry<String, PathItem> entry : distinctTagMap.values()) {
                customOrderMap.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, PathItem> entry : sortedTree.entrySet()) {
                customOrderMap.putIfAbsent(entry.getKey(), entry.getValue());
            }
            sortedPaths.putAll(customOrderMap);
            openApi.setPaths(sortedPaths);

        };
    }

    @Bean
    public OpenApiCustomiser schemaCustomizer() {

        return openApi -> openApi
                // API Schemas
                .schema("AccessDelegation", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(AccessDelegation.class)).schema)
                .schema("AccessDelegationRequest", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(AccessDelegationRequest.class)).schema)
                .schema("AccessPermissions", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(AccessPermission.class)).schema)
                .schema("ActionRequest", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(ActionRequest.class)).schema)
                .schema("AuditTrail", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(AuditTrail.class)).schema)
                .schema("Change", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(Change.class)).schema)
                .schema("ChangeRequest", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(ChangeRequest.class)).schema)
                .schema("Error", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(Error.class)).schema)
                .schema("ErrorDetail", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(ErrorDetail.class)).schema)
                .schema("Notification", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(Notification.class)).schema)
                .schema("NotificationEventType", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(NotificationEventType.class)).schema)
                .schema("Operation", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(Operation.class)).schema)
                .schema("OperationObject", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(OperationObject.class)).schema)
                .schema("PatchOperation", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(PatchOperation.class)).schema)
                .schema("Permission", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(Permission.class)).schema)
                .schema("RequestStatus", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(RequestStatus.class)).schema)
                .schema("ServerInformation", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(ServerInformation.class)).schema)
                .schema("Subscription", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(Subscription.class)).schema)
                .schema("SubscriptionRequest", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(SubscriptionRequest.class)).schema)
                .schema("TopicType", ModelConverters.getInstance()
                        .resolveAsResolvedSchema(new AnnotatedType(TopicType.class)).schema)

//
//                // Cargo Ontology Schemas
//                .schema("ActivitySequence", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(ActivitySequence.class)).schema)
//                .schema("Actor", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Actor.class)).schema)
//                .schema("Address", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Address.class)).schema)
//                .schema("Adjustments", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Adjustments.class)).schema)
//                .schema("Answer", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Answer.class)).schema)
//                .schema("BillingDetails", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BillingDetails.class)).schema)
//                .schema("Booking", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Booking.class)).schema)
//                .schema("BookingOption", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BookingOption.class)).schema)
//                .schema("BookingOptionRequest", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BookingOptionRequest.class)).schema)
//                .schema("BookingRequest", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BookingRequest.class)).schema)
//                .schema("BookingSegment", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BookingSegment.class)).schema)
//                .schema("BookingShipment", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BookingShipment.class)).schema)
//                .schema("BookingTimes", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(BookingTimes.class)).schema)
//                .schema("CO2Emissions", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CO2Emissions.class)).schema)
//                .schema("Carrier", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Carrier.class)).schema)
//                .schema("CarrierProduct", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CarrierProduct.class)).schema)
//                .schema("Characteristic", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Characteristic.class)).schema)
//                .schema("Check", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Check.class)).schema)
//                .schema("CheckTemplate", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CheckTemplate.class)).schema)
//                .schema("CheckTotalResult", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CheckTotalResult.class)).schema)
//                .schema("CommonObjects", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CommonObjects.class)).schema)
//                .schema("Company", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Company.class)).schema)
//                .schema("CompanyBranch", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CompanyBranch.class)).schema)
//                .schema("Composing", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Composing.class)).schema)
//                .schema("ContactDetail", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(ContactDetail.class)).schema)
//                .schema("Country", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Country.class)).schema)
//                .schema("CustomsInformation", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(CustomsInformation.class)).schema)
//                .schema("DgDeclaration", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(DgDeclaration.class)).schema)
//                .schema("DgProductRadioactive", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(DgProductRadioactive.class)).schema)
//                .schema("DgRadioactiveIsotope", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(DgRadioactiveIsotope.class)).schema)
//                .schema("Dimensions", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Dimensions.class)).schema)
//                .schema("EmbeddedObject", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(EmbeddedObject.class)).schema)
//                .schema("EpermitConsignment", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(EpermitConsignment.class)).schema)
//                .schema("EpermitSignature", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(EpermitSignature.class)).schema)
//                .schema("EventUld", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(EventUld.class)).schema)
//                .schema("ExternalReference", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(ExternalReference.class)).schema)
//                .schema("Geolocation", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Geolocation.class)).schema)
//                .schema("HandlingInstructions", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(HandlingInstructions.class)).schema)
//                .schema("Insurance", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Insurance.class)).schema)
//                .schema("IotDevice", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(IotDevice.class)).schema)
//                .schema("Item", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Item.class)).schema)
//                .schema("ItemDg", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(ItemDg.class)).schema)
//                .schema("LiveAnimalsEpermit", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LiveAnimalsEpermit.class)).schema)
//                .schema("Loading", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Loading.class)).schema)
//                .schema("LoadingActivity", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LoadingActivity.class)).schema)
//                .schema("LoadingMaterial", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LoadingMaterial.class)).schema)
//                .schema("LoadingUnit", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LoadingUnit.class)).schema)
//                .schema("Location", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Location.class)).schema)
//                .schema("LogisticsAction", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LogisticsAction.class)).schema)
//                .schema("LogisticsActivity", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LogisticsActivity.class)).schema)
//                .schema("LogisticsAgent", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LogisticsAgent.class)).schema)
//                .schema("LogisticsEvent", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LogisticsEvent.class)).schema)
//                .schema("LogisticsObject", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LogisticsObject.class)).schema)
//                .schema("LogisticsService", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(LogisticsService.class)).schema)
//                .schema("Measurement", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Measurement.class)).schema)
//                .schema("MeasurementsGeoloc", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(MeasurementsGeoloc.class)).schema)
//                .schema("MeasurementsOther", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(MeasurementsOther.class)).schema)
//                .schema("MovementTimes", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(MovementTime.class)).schema)
//                .schema("Moving", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Moving.class)).schema)
//                .schema("NonHumanActor", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(NonHumanActor.class)).schema)
//                .schema("Organization", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Organization.class)).schema)
//                .schema("OtherIdentifier", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(OtherIdentifier.class)).schema)
//                .schema("PackagingType", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(PackagingType.class)).schema)
//                .schema("Party", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Party.class)).schema)
//                .schema("Person", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Person.class)).schema)
//                .schema("PhysicalLogisticsObject", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(PhysicalLogisticsObject.class)).schema)
//                .schema("Piece", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Piece.class)).schema)
//                .schema("PieceDg", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(PieceDg.class)).schema)
//                .schema("PieceLiveAnimals", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(PieceLiveAnimals.class)).schema)
//                .schema("Price", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Price.class)).schema)
//                .schema("Product", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Product.class)).schema)
//                .schema("ProductDg", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(ProductDg.class)).schema)
//                .schema("PublicAuthority", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(PublicAuthority.class)).schema)
//                .schema("Question", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Question.class)).schema)
//                .schema("Ranges", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Ranges.class)).schema)
//                .schema("Ratings", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Ratings.class)).schema)
//                .schema("RegulatedEntity", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(RegulatedEntity.class)).schema)
//                .schema("Routing", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Routing.class)).schema)
//                .schema("ScheduledLegs", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(ScheduledLegs.class)).schema)
//                .schema("SecurityDeclaration", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(SecurityDeclaration.class)).schema)
//                .schema("Sensor", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Sensor.class)).schema)
//                .schema("SensorGeoloc", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(SensorGeoloc.class)).schema)
//                .schema("SensorOther", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(SensorOther.class)).schema)
//                .schema("Shipment", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Shipment.class)).schema)
//                .schema("Storage", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Storage.class)).schema)
//                .schema("Storing", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Storing.class)).schema)
//                .schema("TransportMeans", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(TransportMeans.class)).schema)
//                .schema("TransportMovement", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(TransportMovement.class)).schema)
//                .schema("UnitComposition", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(UnitComposition.class)).schema)
//                .schema("Value", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Value.class)).schema)
//                .schema("VolumetricWeight", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(VolumetricWeight.class)).schema)
//                .schema("Waybill", ModelConverters.getInstance()
//                        .resolveAsResolvedSchema(new AnnotatedType(Waybill.class)).schema)
                ;
    }

   /* public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringShop API")
                        .description("Spring shop sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }*/

}