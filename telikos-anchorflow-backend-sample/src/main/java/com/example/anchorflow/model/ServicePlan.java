package com.example.anchorflow.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicePlan implements Serializable {
  private Product product;
  private String servicePlanNumber;
  private Boolean isMultiCountryConsolidation;
  private Boolean hasDistributionCentre;
  private Boolean hasOriginMilkRun;
  //FCL
  private String cargoServiceType;

}
//{
//    "DataObject": "SERVICE_PLAN",
//    "ServicePlanNumber": "string",
//    "IsMultiCountryConsolidation": true,
//    "HasDistributionCentre": true,
//    "HasOriginMilkRun": true,
//    "HasDestinationMilkRun": true,
//    "TransitTimeMinimum": 0,
//    "TransitTimeMaximum": 0,
//    "TransitTimeUnit": "MILLISECOND",
//    "FreightTerm": "COLLECT",
//    "CargoServiceType": "FCL",
//    "Incoterm": "EXW",
//    "HaulageTerm": "CARRIER",
//    "TransportMode": "MARITIME",
//    "RequiredMainTransportProvider": {
//    "PartyFunction": "OCEAN_CARRIER",
//    "Party": {
//    "PartyCode": "SGMAERSKHQ"
//    }
//    },
//    "ServiceTypeModes": [{
//    "IsMainTransportProviderMode": true,
//    "OriginService": "DOOR",
//    "DestinationService": "DOOR",
//    "LoadService": "CY",
//    "DischargeService": "CY"
//    }],
//    "Product": {
//    "ProductCode": "CCLR",
//    "ProductEnum": "CUSTOMS_CLEARANCE",
//    "ProductName": "Customs Clearance",
//    "ProductDescription": "Maersk handles Customs Clearance for the customer purchasing this product."
//    },
//    "ShipperBooking": {
//    "ShipperBookingNumber": "123456789"
//    },
//    "WarehouseShipment": {
//    "WarehouseShipmentNumber": "WI76522"
//    },
//    "ServicePlanLocations": [{
//    "LocationFunction": "ORIGIN",
//    "Location": {
//    "FacilityCode": "CNSHGTRM"
//    },
//    "ProductComponents": [
//    {
//    "ProductComponentCode": "CCLR",
//    "ProductComponentName": "Customs Clearance"
//    }
//    ]
//    },
//    {
//    "LocationFunction": "DESTINATION",
//    "Location": {
//    "FacilityCode": "USPIER400TRM"
//    },
//    "ProductComponents": [
//    {
//    "ProductComponentCode": "CCLR",
//    "ProductComponentName": "Customs Clearance"
//    }
//    ]
//    }
//    ],
//    "TransportAssetRequirements": [{
//    "DataObject": "TRANSPORT_ASSET_REQUIREMENT",
//    "TransportAssetRequirementTypeEnum": "EQUIPMENT_REQUIREMENT",
//    "RequiredEquipmentSizeTypeCode": "20REEF",
//    "RequiredVehicleTypeCode": "VESSEL",
//    "TransportAssetQuantity": 2,
//    "IsNonOperatingRefrigiratedAsset": false,
//    "Temperature": {
//    "DegreesMinimum": -27,
//    "DegreesMaximum": -10,
//    "DegreesUnit": "CELCIUS",
//    "ProbeCount": 2
//    }
//    }],
//    "ServicePlanLegs": [{
//    "DataObject": "SERVICE_PLAN_LEG",
//    "Sequence": 0,
//    "CargoServiceType": "FCL",
//    "StartLocation": {
//    "LocationFunction": "PORT_OF_LOADING",
//    "Location": {
//    "FacilityCode": "CNSHGTRM"
//    }
//    },
//    "EndLocation": {
//    "LocationFunction": "PORT_OF_DISCHARGE",
//    "Location": {
//    "FacilityCode": "USPIER400TRM"
//    }
//    },
//    "ServicePlanLegType": "MAIN_LEG",
//    "TransportMode": "MARITIME",
//    "RequiredTransportProvider": {
//    "PartyFunction": "OCEAN_CARRIER",
//    "Party": {
//    "PartyCode": "SGMAERSKHQ"
//    }
//    },
//    "ShippingContract": {
//    "ShippingContractNumber": "8373055"
//    },
//    "TransitTimeMinimum": 0,
//    "TransitTimeMaximum": 0,
//    "TransitTimeUnit": "MILLISECOND",
//    "PickupFreeDays": 0,
//    "DeliveryFreeDays": 0,
//    "RequiredPickupService": "DROP_AND_SWAP",
//    "RequiredDeliveryService": "DROP_AND_SWAP",
//    "ServiceDates": [
//    {
//    "Timestamp": "2022-04-01T14:00:00Z",
//    "EventTiming": "EXPECTED",
//    "EventTrigger": "PICK_UP"
//    },
//    {
//    "Timestamp": "2022-04-01T10:00:00Z",
//    "EventTiming": "EXPECTED",
//    "EventTrigger": "PICK_UP_WINDOW_START"
//    },
//    {
//    "Timestamp": "2022-04-01T16:00:00Z",
//    "EventTiming": "EXPECTED",
//    "EventTrigger": "PICK_UP_WINDOW_END"
//    },
//    {
//    "Timestamp": "2022-04-01T19:00:00Z",
//    "EventTiming": "EXPECTED",
//    "EventTrigger": "DELIVERY"
//    },
//    {
//    "Timestamp": "2022-04-01T18:00:00Z",
//    "EventTiming": "EXPECTED",
//    "EventTrigger": "DELIVERY_WINDOW_START"
//    },
//    {
//    "Timestamp": "2022-04-01T20:00:00Z",
//    "EventTiming": "EXPECTED",
//    "EventTrigger": "DELIVERY_WINDOW_END"
//    }
//    ]
//    }]
//    }
