<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet exclude-result-prefixes="#all" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ram="urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:12" xmlns:rsm="urn:ferd:CrossIndustryDocument:invoice:1p0" xmlns:udt="urn:un:unece:uncefact:data:standard:UnqualifiedDataType:15" version="1.0">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" />
	<xsl:template match="/rsm:CrossIndustryDocument">
		<html><body><xsl:apply-templates /></body></html>
   </xsl:template>

    <xsl:template match="rsm:SpecifiedExchangedDocumentContext" />
    <xsl:template match="rsm:HeaderExchangedDocument">
		<h1><xsl:value-of select="ram:Name" /> <xsl:value-of select="ram:ID" /></h1>
		<xsl:apply-templates select="ram:IssueDateTime" />
	</xsl:template>
    <xsl:template match="rsm:SpecifiedSupplyChainTradeTransaction">
		<table width="100%" border="0">
		<tr>
		<td><xsl:for-each select="ram:ApplicableSupplyChainTradeAgreement/ram:SellerTradeParty">
			<xsl:call-template name="address" />
		</xsl:for-each>
		</td>
		<td><xsl:for-each select="ram:ApplicableSupplyChainTradeAgreement/ram:BuyerTradeParty">
			<xsl:call-template name="address" />
		</xsl:for-each>
		</td>
		</tr>
		</table>
		<table width="100%">
		<tr><th>#</th><th>Product</th><th>Unit</th><th>Qty.</th><th>Sub.</th><th>Tax%</th><th>Tax</th><th>Total</th><th>Curr.</th></tr>
		<xsl:for-each select="ram:IncludedSupplyChainTradeLineItem">
		<tr>
		<td align="Right"><xsl:value-of select="ram:AssociatedDocumentLineDocument/ram:LineID" /></td>
		<td><xsl:value-of select="ram:SpecifiedTradeProduct/ram:Name" /></td>
		<td align="Right"><xsl:call-template name="twodecimals"><xsl:with-param name="number" select="ram:SpecifiedSupplyChainTradeAgreement/ram:GrossPriceProductTradePrice/ram:ChargeAmount" /></xsl:call-template></td>
		<td align="Right"><xsl:call-template name="twodecimals"><xsl:with-param name="number" select="ram:SpecifiedSupplyChainTradeDelivery/ram:BilledQuantity" /></xsl:call-template></td>
		<td align="Right"><xsl:value-of select="ram:SpecifiedSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:LineTotalAmount" /></td>
		<td align="Right"><xsl:value-of select="ram:SpecifiedSupplyChainTradeSettlement/ram:ApplicableTradeTax/ram:ApplicablePercent" />%</td>
		<td align="Right"><xsl:call-template name="calcTax">
		<xsl:with-param name="basis" select="ram:SpecifiedSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:LineTotalAmount" />
		<xsl:with-param name="tax" select="ram:SpecifiedSupplyChainTradeSettlement/ram:ApplicableTradeTax/ram:ApplicablePercent" />
		</xsl:call-template></td>
		<td align="Right"><xsl:call-template name="calcWithTax">
		<xsl:with-param name="basis" select="ram:SpecifiedSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:LineTotalAmount" />
		<xsl:with-param name="tax" select="ram:SpecifiedSupplyChainTradeSettlement/ram:ApplicableTradeTax/ram:ApplicablePercent" />
		</xsl:call-template></td>
		<td><xsl:value-of select="ram:SpecifiedSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:LineTotalAmount/@currencyID" /></td>
		</tr>
		</xsl:for-each>
		<xsl:for-each select="ram:ApplicableSupplyChainTradeSettlement/ram:ApplicableTradeTax">
		<tr>
		<td align="Right" colspan="6"><xsl:value-of select="ram:TypeCode" />: <xsl:value-of select="ram:ApplicablePercent" />%</td>
		<td align="Right"><xsl:value-of select="ram:CalculatedAmount" /></td>
		<td align="Right"><xsl:value-of select="ram:BasisAmount" /></td>
		<td><xsl:value-of select="ram:CalculatedAmount/@currencyID" /></td>
		</tr>
		</xsl:for-each>
		<tr>
		<td align="Right" colspan="6">Totals:</td>
		<td align="Right"><xsl:value-of select="ram:ApplicableSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:TaxTotalAmount" /></td>
		<td align="Right"><xsl:value-of select="ram:ApplicableSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:TaxBasisTotalAmount" /></td>
		<td><xsl:value-of select="ram:ApplicableSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:TaxBasisTotalAmount/@currencyID" /></td>
		</tr>
		<tr>
		<td align="Right" colspan="7">Grand total:</td>
		<td align="Right"><xsl:value-of select="ram:ApplicableSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:GrandTotalAmount" /></td>
		<td><xsl:value-of select="ram:ApplicableSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementMonetarySummation/ram:GrandTotalAmount/@currencyID" /></td>
		<td></td>
		</tr>
		</table>
		<table border="0" align="Right">
		<tr><td colspan="3">Please pay to one of the following accounts using the following reference: <xsl:value-of select="ram:ApplicableSupplyChainTradeSettlement/ram:PaymentReference" /></td></tr>
		<tr><th>Bank</th><th>BIC ID</th><th>IBAN Number</th></tr>
		<xsl:for-each select="ram:ApplicableSupplyChainTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans">
		<tr>
		<td><xsl:value-of select="ram:PayeeSpecifiedCreditorFinancialInstitution/ram:Name" /></td>
		<td><xsl:value-of select="ram:PayeeSpecifiedCreditorFinancialInstitution/ram:BICID" /></td>
		<td><xsl:value-of select="ram:PayeePartyCreditorFinancialAccount/ram:IBANID" /></td>
		</tr>
		</xsl:for-each>
		</table>
	</xsl:template>
	
	
	<xsl:template match="udt:DateTimeString">
		<h2><xsl:choose>
			<xsl:when test="@format='610'"><xsl:call-template name="YYYYMM"><xsl:with-param name="date" select="." /></xsl:call-template></xsl:when>
			<xsl:when test="@format='616'"><xsl:call-template name="YYYYWW"><xsl:with-param name="date" select="." /></xsl:call-template></xsl:when>
			<xsl:otherwise><xsl:call-template name="YYYYMMDD"><xsl:with-param name="date" select="." /></xsl:call-template></xsl:otherwise>
		</xsl:choose></h2>
	</xsl:template>
	
	<xsl:template name="address">
		<div><xsl:value-of select="ram:Name" /></div>
		<div><xsl:value-of select="ram:PostalTradeAddress/ram:LineOne" /><br />
		<xsl:value-of select="ram:PostalTradeAddress/ram:LineTwo" /><br />
		<xsl:value-of select="ram:PostalTradeAddress/ram:CountryID" />-<xsl:value-of select="ram:PostalTradeAddress/ram:PostcodeCode" /><xsl:text> </xsl:text><xsl:value-of select="ram:PostalTradeAddress/ram:CityName" /></div>
		<div><xsl:for-each select="ram:SpecifiedTaxRegistration/ram:ID">
			<xsl:value-of select="@schemeID" />: <xsl:value-of select="." /><br />
		</xsl:for-each></div>
	</xsl:template>
	
	<xsl:template name="calcTax">
        <xsl:param name="basis" />
        <xsl:param name="tax" />
		<xsl:call-template name="twodecimals"><xsl:with-param name="number" select="($basis * $tax) div 100" /></xsl:call-template>
	</xsl:template>
	<xsl:template name="calcWithTax">
        <xsl:param name="basis" />
        <xsl:param name="tax" />
		<xsl:call-template name="twodecimals"><xsl:with-param name="number" select="$basis + (($basis * $tax) div 100)" /></xsl:call-template>
	</xsl:template>
	<xsl:template name="twodecimals">
        <xsl:param name="number" />
		<xsl:value-of select="format-number(round($number*100) div 100, '0.00')"/>
	</xsl:template>
	<xsl:template name="YYYYMMDD">
        <xsl:param name="date" />
		<xsl:value-of select="substring($date,1,4)" />-<xsl:value-of select="substring($date,5,2)" />-<xsl:value-of select="substring($date,7,2)" />
    </xsl:template>
	<xsl:template name="YYYYMM">
        <xsl:param name="date" />
		<xsl:value-of select="substring($date,1,4)" />-<xsl:value-of select="substring($date,5,2)" />
    </xsl:template>
	<xsl:template name="YYYYWW">
        <xsl:param name="date" />
		<xsl:value-of select="substring($date,1,4)" />; week <xsl:value-of select="substring($date,5,2)" />
    </xsl:template>
</xsl:stylesheet>