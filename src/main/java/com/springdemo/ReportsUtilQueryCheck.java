package com.springdemo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ReportsUtilQueryCheck {
        private static Map<String, String> declarations = new LinkedHashMap<>();

        private static Map<String, String> items = new LinkedHashMap<>();

        private static String import_entry_headers = "Reference,D. Type,Entry No,Entry Date,Route,ICS,SOE,Total Duty,Total VAT,Inventory Ref,No. Pkgs,Location,CWC,Consignee Name,Consignee EORI,Consignor Name,Consignor EORI,Agent Name,Agent EORI,Warehouse ID,Invoice Value,Invoice Currency,Items,Previous Documents";

        private static String import_item_headers = "Reference,D. Type,Entry No,Entry Date,Route,ICS,SOE,Total Duty,Total VAT,Inventory Ref,No. Pkgs,Location,Consignee Name,Consignee EORI,Consignor Name,Consignor EORI,Agent Name,Agent EORI,Warehouse ID,CWC,Invoice Value,Invoice Currency,Item No,Commodity Code,Description,Value,Qty1,Qty2,Qty3,CPC,Preference,Val. Method,Packages,Item Duty,Item VAT,Previous Documents";

        private static String export_entry_headers = "Reference,D. Type,Entry No,Entry Date,Route,ICS,SOE,MUCR,No. Pkgs,Location,Disp Country,Dest Country,Consignor Name,Consignor EORI,Consignee Name,Consignee EORI,Agent Name,Agent EORI,Warehouse ID,Invoice Currency,Items,Previous Documents";

        private static String export_item_headers = "Reference,D. Type,Entry No,Entry Date,Route,ICS,SOE,MUCR,No. Pkgs,Location,Consignor Name,Consignor EORI,Consignee Name,Consignee EORI,Agent Name,Agent EORI,Warehouse ID,Disp Country,Dest Country,Dec. Currency,Item No,Commodity Code,Description,Stat Value,Gross,Qty1,Qty2,Qty3,CPC,Packages,Previous Documents";

        private static String entries_by_consignee_headers = "Consignee Name,No. Entries,Total Items,Duty Paid,VAT Paid";

        private static String entries_by_consignor_headers = "Consignor Name,No. Entries,Total Items,Total Stat Value";

        private static String entries_by_user_headers = "User Name,No. Entries,No. Items";

        private static String cfsp_headers = "Entry Type,SDP Entry Ref,SDP Status Code,SDP MRN,SDP LRN,SDP Entry Date,SDP Acceptance Date,SDP DUCR,SFD Entry Ref,SFD Status Code,SFD MRN,SFD LRN,SFD Acceptance Date,SFD DUCR,Rep Check";

        private static String import_entry_breakdown = "SELECT 	row_number() over (ORDER BY declaration.declaration_id) AS sno,\r\n"
                + "			declaration.declaration_reference AS reference,\r\n"
                + "			declaration.type_code AS declarationType,\r\n"
                + "			declaration.id AS entryNumber,\r\n"
                + "			to_char(declaration.entry_acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS entryDate,\r\n"
                + "			declaration.route_code AS route,\r\n"
                + "			declaration.entry_status_code AS ics,\r\n"
                + "			declaration.status_of_entry AS soe,\r\n"
                + "			declaration.total_customs_duty_payable AS totalDuty,\r\n"
                + "			declaration.vat_adjustment_amount AS totalVat,\r\n"
                + "			declaration.inventory_number AS inventoryRef,\r\n"
                + "			declaration.total_package_quantity AS noOfPackages,\r\n"
                + "			goodsLocation.location_code AS location,\r\n"
                + "			header.export_country_id AS cwc,\r\n"
                + "			consignee.name AS consigneeName,\r\n"
                + "			consignee.id AS consigneeEori,\r\n"
                + "			consignor.name AS consignorName,\r\n"
                + "			consignor.id AS consignorEori,\r\n"
                + "			agent.name AS agentName,\r\n"
                + "			agent.id AS agentEori,\r\n"
                + "			warehouse.id AS warehouseId,\r\n"
                + "			declaration.invoice_amount AS invoiceValue,\r\n"
                + "			declaration.invoice_amount_currency_id AS invoiceCurrency,\r\n"
                + "			declaration.goods_item_quantity AS items,\r\n"
                + "			string_agg(NULLIF(TRIM(CONCAT(NULLIF(prevDocument.category_code,''),' ',NULLIF(prevDocument.type_code,''),' ',NULLIF(prevDocument.id,''),' ',prevDocument.line_numeric)),''),', ') AS prevDoc\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.header header ON header.header_id=declaration.header_id\r\n"
                + "	LEFT JOIN cds_schema.goods_location goodsLocation ON goodsLocation.goods_location_id=declaration.goods_location_id\r\n"
                + "	LEFT JOIN cds_schema.consignee consignee ON consignee.consignee_id=declaration.consignee_id\r\n"
                + "	LEFT JOIN cds_schema.consignor consignor ON consignor.consignor_id=declaration.consignor_id\r\n"
                + "	LEFT JOIN cds_schema.agent agent ON agent.agent_id=declaration.agent_id\r\n"
                + "	LEFT JOIN cds_schema.warehouse warehouse ON warehouse.warehouse_id=declaration.warehouse_id\r\n"
                + "	LEFT JOIN cds_schema.previous_document prevDocument ON prevDocument.declaration_id = declaration.declaration_id\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + "	AND declaration.declaration_type NOT IN ('COE', 'EX') ";

        private static String import_item_breakdown = "SELECT row_number() over (ORDER BY declaration.declaration_id) AS sno,\r\n"
                + "			declaration.declaration_reference AS reference,\r\n"
                + "			declaration.type_code AS declarationType,\r\n"
                + "			declaration.id AS entryNumber,\r\n"
                + "			to_char(declaration.entry_acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS entryDate,\r\n"
                + "			declaration.route_code AS route,\r\n"
                + "			declaration.entry_status_code AS ics,\r\n"
                + "			declaration.status_of_entry AS soe,\r\n"
                + "			declaration.total_customs_duty_payable AS totalDuty,\r\n"
                + "			declaration.vat_adjustment_amount AS totalVat,\r\n"
                + "			declaration.inventory_number AS inventoryRef,\r\n"
                + "			declaration.total_package_quantity AS noOfPackages,\r\n"
                + "			goodsLocation.location_code AS location,\r\n"
                + "			consignee.name AS consigneeName,\r\n"
                + "			consignee.id AS consigneeEori,\r\n"
                + "			consignor.name AS consignorName,\r\n"
                + "			consignor.id AS consignorEori,\r\n"
                + "			agent.name AS agentName,\r\n"
                + "			agent.id AS agentEori,\r\n"
                + "			warehouse.id AS warehouseId,\r\n"
                + "			header.export_country_id AS cwc,\r\n"
                + "			declaration.invoice_amount AS invoiceValue,\r\n"
                + "			declaration.invoice_amount_currency_id AS invoiceCurrency,\r\n"
                + "			governmentAgencyGoodsItem.sequence_numeric AS itemNumber,\r\n"
                + "			governmentAgencyGoodsItem.commodity_code AS commodityCode,\r\n"
                + "			governmentAgencyGoodsItem.description AS description,\r\n"
                + "			governmentAgencyGoodsItem.item_charge_amount AS value,\r\n"
                + "			governmentAgencyGoodsItem.net_net_weight_measure AS qty1,\r\n"
                + "			governmentAgencyGoodsItem.tariff_quantity AS qty2,\r\n"
                + "			governmentAgencyGoodsItem.qty3 AS qty3,\r\n"
                + "			governmentAgencyGoodsItem.cpc AS cpc,\r\n"
                + "			governmentAgencyGoodsItem.preference AS preference,\r\n"
                + "			governmentAgencyGoodsItem.customs_valuation_method_code AS valuationMethod,\r\n"
                + "			governmentAgencyGoodsItem.packages AS packages,\r\n"
                + "			governmentAgencyGoodsItem.item_duty AS itemDuty,\r\n"
                + "			governmentAgencyGoodsItem.item_tax_assessed_vat AS itemVat,\r\n"
                + "			string_agg(NULLIF(TRIM(CONCAT(NULLIF(prevDocument.category_code,''),' ',NULLIF(prevDocument.type_code,''),' ',NULLIF(prevDocument.id,''),' ',prevDocument.line_numeric)),''),', ') AS prevDoc\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.header header ON header.header_id=declaration.header_id\r\n"
                + "	LEFT JOIN cds_schema.goods_location goodsLocation ON goodsLocation.goods_location_id=declaration.goods_location_id\r\n"
                + "	LEFT JOIN cds_schema.government_agency_goods_item governmentAgencyGoodsItem ON governmentAgencyGoodsItem.declaration_id=declaration.declaration_id\r\n"
                + "	LEFT JOIN cds_schema.consignee consignee ON consignee.consignee_id=(CASE WHEN declaration.consignee_id IS NOT NULL THEN declaration.consignee_id ELSE governmentAgencyGoodsItem.consignee_id END)\r\n"
                + "	LEFT JOIN cds_schema.consignor consignor ON consignor.consignor_id=(CASE WHEN declaration.consignor_id IS NOT NULL THEN declaration.consignor_id ELSE governmentAgencyGoodsItem.consignor_id END)\r\n"
                + "	LEFT JOIN cds_schema.agent agent ON agent.agent_id=declaration.agent_id\r\n"
                + "	LEFT JOIN cds_schema.warehouse warehouse ON warehouse.warehouse_id=declaration.warehouse_id\r\n"
                + "	LEFT JOIN cds_schema.previous_document prevDocument ON prevDocument.item_id = governmentAgencyGoodsItem.item_id\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + "	AND declaration.declaration_type NOT IN ('COE', 'EX') ";

        private static String export_entry_breakdown = "SELECT row_number() over (ORDER BY declaration.declaration_id) AS sno,\r\n"
                + "			declaration.declaration_reference AS reference,\r\n"
                + "			declaration.type_code AS declarationType,\r\n"
                + "			declaration.id AS entryNumber,\r\n"
                + "			to_char(declaration.entry_acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS entryDate,\r\n"
                + "			declaration.route_code AS route,\r\n"
                + "			declaration.entry_status_code AS ics,\r\n"
                + "			declaration.status_of_entry AS soe,\r\n"
                + "			declaration.inventory_number AS inventoryRef,\r\n"
                + "			declaration.total_package_quantity AS noOfPackages,\r\n"
                + "			goodsLocation.location_code AS location,\r\n"
                + "			header.export_country_id AS dispatchCountry,\r\n"
                + "			header.destination_country_code AS destinationCountry,\r\n"
                + "			consignor.name AS consignorName,\r\n"
                + "			consignor.id AS consignorEori,\r\n"
                + "			consignee.name AS consigneeName,\r\n"
                + "			consignee.id AS consigneeEori,\r\n"
                + "			agent.name AS agentName,\r\n"
                + "			agent.id AS agentEori,\r\n"
                + "			warehouse.id AS warehouseId,\r\n"
                + "			declaration.invoice_amount_currency_id AS invoiceCurrency,\r\n"
                + "			declaration.goods_item_quantity AS items,\r\n"
                + "			string_agg(NULLIF(TRIM(CONCAT(NULLIF(prevDocument.category_code,''),' ',NULLIF(prevDocument.type_code,''),' ',NULLIF(prevDocument.id,''),' ',prevDocument.line_numeric)),''),', ') AS prevDoc\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.header header ON header.header_id=declaration.header_id\r\n"
                + "	LEFT JOIN cds_schema.goods_location goodsLocation ON goodsLocation.goods_location_id=declaration.goods_location_id\r\n"
                + "	LEFT JOIN cds_schema.consignor consignor ON consignor.consignor_id=declaration.consignor_id\r\n"
                + "	LEFT JOIN cds_schema.consignee consignee ON consignee.consignee_id=declaration.consignee_id\r\n"
                + "	LEFT JOIN cds_schema.agent agent ON agent.agent_id=declaration.agent_id\r\n"
                + "	LEFT JOIN cds_schema.warehouse warehouse ON warehouse.warehouse_id=declaration.warehouse_id\r\n"
                + "	LEFT JOIN cds_schema.previous_document prevDocument ON prevDocument.declaration_id = declaration.declaration_id\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + "	AND declaration.declaration_type IN ('COE', 'EX') ";

        private static String export_item_breakdown = "SELECT row_number() over (ORDER BY declaration.declaration_id) AS sno,\r\n"
                + "			declaration.declaration_reference AS reference,\r\n"
                + "			declaration.type_code AS declarationType,\r\n"
                + "			declaration.id AS entryNumber,\r\n"
                + "			to_char(declaration.entry_acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS entryDate,\r\n"
                + "			declaration.route_code AS route,\r\n"
                + "			declaration.entry_status_code AS ics,\r\n"
                + "			declaration.status_of_entry AS soe,\r\n"
                + "			declaration.inventory_number AS inventoryRef,\r\n"
                + "			declaration.total_package_quantity AS noOfPackages,\r\n"
                + "			goodsLocation.location_code AS location,\r\n"
                + "			consignor.name AS consignorName,\r\n"
                + "			consignor.id AS consignorEori,\r\n"
                + "			consignee.name AS consigneeName,\r\n"
                + "			consignee.id AS consigneeEori,\r\n"
                + "			agent.name AS agentName,\r\n"
                + "			agent.id AS agentEori,\r\n"
                + "			warehouse.id AS warehouseId,\r\n"
                + "			header.export_country_id AS dispatchCountry,\r\n"
                + "			(CASE WHEN header.destination_country_code IS NOT NULL THEN header.destination_country_code ELSE governmentAgencyGoodsItem.destination_country_code END) AS destinationCountry,\r\n"
                + "			declaration.invoice_amount_currency_id AS declarationCurrency,\r\n"
                + "			governmentAgencyGoodsItem.sequence_numeric AS itemNumber,\r\n"
                + "			governmentAgencyGoodsItem.commodity_code AS commodityCode,\r\n"
                + "			governmentAgencyGoodsItem.description AS description,\r\n"
                + "			governmentAgencyGoodsItem.statistical_value_amount AS statValue,\r\n"
                + "			governmentAgencyGoodsItem.gross_mass_measure AS grossMass,\r\n"
                + "			governmentAgencyGoodsItem.net_net_weight_measure AS qty1,\r\n"
                + "			governmentAgencyGoodsItem.tariff_quantity AS qty2,\r\n"
                + "			governmentAgencyGoodsItem.qty3 AS qty3,\r\n"
                + "			governmentAgencyGoodsItem.cpc AS cpc,\r\n"
                + "			governmentAgencyGoodsItem.packages AS packages,\r\n"
                + "			string_agg(NULLIF(TRIM(CONCAT(NULLIF(prevDocument.category_code,''),' ',NULLIF(prevDocument.type_code,''),' ',NULLIF(prevDocument.id,''),' ',prevDocument.line_numeric)),''),', ') AS prevDoc\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.header header ON header.header_id=declaration.header_id\r\n"
                + "	LEFT JOIN cds_schema.goods_location goodsLocation ON goodsLocation.goods_location_id=declaration.goods_location_id\r\n"
                + "	LEFT JOIN cds_schema.government_agency_goods_item governmentAgencyGoodsItem ON governmentAgencyGoodsItem.declaration_id=declaration.declaration_id\r\n"
                + "	LEFT JOIN cds_schema.consignor consignor ON consignor.consignor_id=(CASE WHEN declaration.consignor_id IS NOT NULL THEN declaration.consignor_id ELSE governmentAgencyGoodsItem.consignor_id END)\r\n"
                + "	LEFT JOIN cds_schema.consignee consignee ON consignee.consignee_id=(CASE WHEN declaration.consignee_id IS NOT NULL THEN declaration.consignee_id ELSE governmentAgencyGoodsItem.consignee_id END)\r\n"
                + "	LEFT JOIN cds_schema.agent agent ON agent.agent_id=declaration.agent_id\r\n"
                + "	LEFT JOIN cds_schema.warehouse warehouse ON warehouse.warehouse_id=declaration.warehouse_id\r\n"
                + "	LEFT JOIN cds_schema.previous_document prevDocument ON prevDocument.item_id = governmentAgencyGoodsItem.item_id\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + "	AND declaration.declaration_type IN ('COE', 'EX') ";

        private static String entries_by_consignee = "SELECT row_number() over (ORDER BY consignee.name) AS sno,  \r\n"
                + "			consignee.name AS consigneeName, \r\n"
                + "			COUNT(DISTINCT declaration.declaration_id) AS noEntries, \r\n"
                + "			COUNT(DISTINCT governmentAgencyGoodsItem.item_id) AS totalItems,\r\n"
                + "			SUM(declaration.total_customs_duty_payable) AS dutyPaid, \r\n"
                + "			SUM(declaration.vat_adjustment_amount) AS vatPaid\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.government_agency_goods_item governmentAgencyGoodsItem ON governmentAgencyGoodsItem.declaration_id=declaration.declaration_id\r\n"
                + "	LEFT JOIN cds_schema.consignee consignee ON consignee.consignee_id=(CASE WHEN declaration.consignee_id IS NOT NULL THEN declaration.consignee_id ELSE governmentAgencyGoodsItem.consignee_id END)\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + "	AND declaration.declaration_type NOT IN ('COE', 'EX') ";

        private static String entries_by_consignor = "SELECT row_number() over (ORDER BY consignor.name) AS sno,\r\n"
                + "			consignor.name AS consignorName, \r\n"
                + "			COUNT(DISTINCT declaration.declaration_id) AS noEntries, \r\n"
                + "			COUNT(DISTINCT governmentAgencyGoodsItem.item_id) AS totalItems,\r\n"
                + "			SUM(governmentAgencyGoodsItem.statistical_value_amount) AS totalStatValue\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.government_agency_goods_item governmentAgencyGoodsItem ON governmentAgencyGoodsItem.declaration_id=declaration.declaration_id\r\n"
                + "	LEFT JOIN cds_schema.consignor consignor ON consignor.consignor_id=(CASE WHEN declaration.consignor_id IS NOT NULL THEN declaration.consignor_id ELSE governmentAgencyGoodsItem.consignor_id END)\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + "	AND declaration.declaration_type IN ('COE', 'EX')";

        private static String entries_by_user = "SELECT row_number() over (ORDER BY declaration.client_code) AS sno, \r\n"
                + "			declaration.client_code AS userName, \r\n"
                + "			COUNT(DISTINCT declaration.declaration_id) AS noOfEntries, \r\n"
                + "			COUNT(DISTINCT governmentAgencyGoodsItem.item_id) AS noOfItems\r\n"
                + "	FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.government_agency_goods_item governmentAgencyGoodsItem ON governmentAgencyGoodsItem.declaration_id=declaration.declaration_id\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'";

        private static String cfsp_report_sql = "SELECT row_number() over (ORDER BY declaration.declaration_id) AS sno,\r\n"
                + "			declaration.type_code AS entryType,\r\n"
                + "			declaration.declaration_reference AS sdpEntryRef,\r\n"
                + "			sdpAgent.function_code AS sdpStatusCode,\r\n"
                + "			declaration.id AS sdpMRN,\r\n"
                + "			declaration.functional_reference_id  AS sdpLRN,\r\n"
                + "			to_char(declaration.entry_acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS sdpEntryDate,\r\n"
                + "			to_char(declaration.acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS sdpAcceptanceDate,\r\n"
                + "			sdpPreviousDocument.sdpDUCR AS sdpDUCR,\r\n"
                + "			sfdDeclaration.declaration_reference AS sfdEntryRef,\r\n"
                + "			sfdAgent.function_code AS sfdStatusCode,\r\n"
                + "			sfdDeclaration.id AS sfdMRN,\r\n"
                + "			sfdDeclaration.functional_reference_id  AS sfdLRN,\r\n"
                + "			to_char(sfdDeclaration.entry_acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS sfdAcceptanceDate,\r\n"
//			+ "			to_char(sfdDeclaration.acceptance_date_time, 'yyyy-MM-dd HH24:MI') AS sfdAcceptanceDate,\r\n"
                + "			sfdPreviousDocument.sdpDUCR AS sfdDUCR,\r\n"
//			+ "			CASE WHEN(sfdDeclaration.declaration_id IS NULL) THEN 'SFD Not Found' WHEN((to_char(declaration.acceptance_date_time, 'yyyy-MM-dd') = to_char(sfdDeclaration.acceptance_date_time, 'yyyy-MM-dd')) = true) THEN 'Ok' ELSE 'Error' END AS acceptanceDateCheck,\r\n"
                + "			CASE WHEN(sfdDeclaration.declaration_id IS NULL) THEN 'SFD Not Found' WHEN((sdpAgent.function_code = sfdAgent.function_code) = TRUE) THEN 'Ok' ELSE 'Error' END AS repCheck\r\n"
//			+ "			CASE WHEN(sfdDeclaration.declaration_id IS NULL) THEN 'SFD Not Found' WHEN((sdpPreviousDocument.sdpDUCR = sfdPreviousDocument.sdpDUCR) = TRUE) THEN 'Ok' ELSE 'Error' END AS ducrCheck\r\n"
                + " FROM cds_schema.declaration declaration\r\n"
                + "	LEFT JOIN cds_schema.agent sdpAgent ON sdpAgent.agent_id=declaration.agent_id\r\n"
                + "	LEFT JOIN cds_schema.consignor consignor ON consignor.consignor_id=declaration.consignor_id\r\n"
                + "	LEFT JOIN cds_schema.consignee consignee ON consignee.consignee_id=declaration.consignee_id\r\n"
                + "	LEFT JOIN (\r\n"
                + "		SELECT string_agg(previousDocument.id, ',') AS sdpDUCR, previousDocument.declaration_id \r\n"
                + "		FROM cds_schema.previous_document previousDocument \r\n"
                + "		WHERE previousDocument.type_code = 'DCR' AND previousDocument.category_code = 'Z'\r\n"
                + "		GROUP BY previousDocument.declaration_id\r\n"
                + "	) AS sdpPreviousDocument ON sdpPreviousDocument.declaration_id = declaration.declaration_id\r\n"
                + "	LEFT JOIN cds_schema.declaration sfdDeclaration ON sfdDeclaration.declaration_id = declaration.sdp_declaration_id\r\n"
                + "	LEFT JOIN cds_schema.agent sfdAgent ON sfdAgent.agent_id=sfdDeclaration.agent_id\r\n"
                + "	LEFT JOIN (\r\n"
                + "		SELECT string_agg(previousDocument.id, ',') AS sdpDUCR, previousDocument.declaration_id \r\n"
                + "		FROM cds_schema.previous_document previousDocument \r\n"
                + "		WHERE previousDocument.type_code = 'DCR' AND previousDocument.category_code = 'Z'\r\n"
                + "		GROUP BY previousDocument.declaration_id\r\n"
                + "	) AS sfdPreviousDocument ON sfdPreviousDocument.declaration_id = sfdDeclaration.declaration_id\r\n"
                + "	WHERE declaration.client_code= :clientCode AND declaration.declaration_status!='UK_DEC_ENT_DRAFT'\r\n"
                + " AND declaration.type_code IN ('IMY', 'IMZ') AND declaration.procedure_category = 'I1' ";

        static {

            declarations.put("Reference", "declarationReference");
            declarations.put("D. Type", "header.declarationType");
            declarations.put("Entry No", "header.entryNumber");
            declarations.put("Route", "header.routeCode");
            declarations.put("ICS", "header.entryStatusCode");
            declarations.put("SOE", "header.statusOfEntry");
            declarations.put("Total Duty", "header.totalCustomsDutyPayable");
            declarations.put("Total VAT", "header.totalVatPayable");
            declarations.put("Inventory Ref", "header.masterUcr");
            declarations.put("No. Pkgs", "header.totalPackages");
            declarations.put("Location", "header.locationofGoods");
            declarations.put("Consignee Name", "consignee.name");
            declarations.put("Consignee EORI", "consignee.eori");
            declarations.put("Consignor Name", "consignor.consignorName");
            declarations.put("Consignor EORI", "consignor.consignorEORI");
            declarations.put("Agent Name", "declarant.declarantName");
            declarations.put("Agent EORI", "declarant.declarantEORI");
            declarations.put("Warehouse ID", "premises.premisesIDWHID");
            declarations.put("Invoice Value", "header.totalInvoiceAmount");
            declarations.put("Invoice Currency", "header.invoiceCurrency");
            declarations.put("Items", "");

            items.put("Reference", "declarationReference");
            items.put("D. Type", "header.declarationType");
            items.put("Entry No", "header.entryNumber");
            items.put("Route", "header.routeCode");
            items.put("ICS", "header.entryStatusCode");
            items.put("SOE", "header.statusOfEntry");
            items.put("Total Duty", "header.totalCustomsDutyPayable");
            items.put("Total VAT", "header.totalVatPayable");
            items.put("Inventory Ref", "header.masterUcr");
            items.put("No. Pkgs", "header.totalPackages");
            items.put("Location", "header.locationofGoods");
            items.put("Consignee Name", "consignee.name");
            items.put("Consignee EORI", "consignee.eori");
            items.put("Consignor Name", "consignor.consignorName");
            items.put("Consignor EORI", "consignor.consignorEORI");
            items.put("Agent Name", "declarant.declarantName");
            items.put("Agent EORI", "declarant.declarantEORI");
            items.put("Warehouse ID", "premises.premisesIDWHID");
            items.put("Invoice Value", "header.totalInvoiceAmount");
            items.put("Invoice Currency", "header.invoiceCurrency");
            items.put("Item No", "items[%d].itemNo");
            items.put("Commodity Code", "items[%d].commodityCode");
            items.put("Description", "items[%d].goodsDescription");
            items.put("Value", "items[%d].netPrice");
            items.put("Qty1", "items[%d].netMass");
            items.put("Qty2", "items[%d].supplementaryUnitsQty2");
            items.put("Qty3", "items[%d].thirdQuantityQty3");
            items.put("CPC", "items[%d].customsProcedureCode");
            items.put("Preference", "items[%d].preferenceCode");
            items.put("Val. Method", "items[%d].valuationMethodCode");
            items.put("Item Duty", "items[%d].duty");
            items.put("Item VAT", "items[%d].vat");

        }

        public static String getQuery(String reportType) {
            String query = null;
            switch (reportType) {

                case "Import Entry Breakdown":
                    query = import_entry_breakdown;
                    break;

                case "Import Item Breakdown":
                    query = import_item_breakdown;
                    break;

                case "Export Entry Breakdown":
                    query = export_entry_breakdown;
                    break;

                case "Export Item Breakdown":
                    query = export_item_breakdown;
                    break;

                case "Entries By Consignee(Imports)":
                    query = entries_by_consignee;
                    break;

                case "Entries By Consignor(Exports)":
                    query = entries_by_consignor;
                    break;

                case "User Entry Report":
                    query = entries_by_user;
                    break;

                case "CFSP Report":
                    query = cfsp_report_sql;
                    break;

                default:
                    break;
            }
            return query;
        }

        public static void addHeaders(List<String[]> list, String reportType) {

            switch (reportType) {

                case "Import Entry Breakdown":
                    list.add(import_entry_headers.split(","));
                    break;

                case "Import Item Breakdown":
                    list.add(import_item_headers.split(","));
                    break;

                case "Export Entry Breakdown":
                    list.add(export_entry_headers.split(","));
                    break;

                case "Export Item Breakdown":
                    list.add(export_item_headers.split(","));
                    break;

                case "Entries By Consignee(Imports)":
                    list.add(entries_by_consignee_headers.split(","));
                    break;

                case "Entries By Consignor(Exports)":
                    list.add(entries_by_consignor_headers.split(","));
                    break;

                case "User Entry Report":
                    list.add(entries_by_user_headers.split(","));
                    break;

                case "CFSP Report":
                    list.add(cfsp_headers.split(","));
                    break;

                default:
                    break;
            }
        }

   /* public static void main(String[] args) {
        System.out.println(" Reports Utl Check");
        String qry = getQuery("Import Entry Breakdown");
        System.out.println("Query \n \n "+qry);
        }

        */

}



