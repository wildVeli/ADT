<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match='/'>
		<html><xsl:apply-templates/></html>
	</xsl:template>

	<xsl:template match='ADT'>
		<head><title> LISTADO DE USUARIOS </title></head>
		<link rel="stylesheet" type="text/css" href="./estilos.css"/>
		<body style="background-color:powderblue;">
			<h1 align='center'>LISTA DE USUARIOS</h1>
			<table border='1' align='center'>
				<tr ><th color='yellow'>id</th><th>password</th></tr>
				<xsl:apply-templates select = 'usuario'/>
			</table>
		</body>
	</xsl:template>

	<xsl:template match='usuario'>
		<tr><xsl:apply-templates/></tr>
	</xsl:template>

	<xsl:template match='id|password'>
		<td><xsl:apply-templates /></td>
	</xsl:template>

</xsl:stylesheet>
