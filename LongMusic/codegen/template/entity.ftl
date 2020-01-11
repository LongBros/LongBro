<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign comment=model.tabComment>
<#assign subtables=model.subTableList>
<#assign pk=func.getPk(model) >
<#assign pkModel=model.pkModel >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign pkType=func.getPkType(model)>
<#assign fkType=func.getFkType(model)>
<#assign system=vars.system>
<#assign domain=vars.domain>
<#assign tableName=model.tableName>
<#assign colList=model.columnList>
<#assign commonList=model.commonList>
<#assign foreignField="">
<#if (model.sub)>
	<#assign foreignField=model.foreignKey?lower_case>
</#if>
package ${system}.${domain}.${package}.bean;
/**
 *  
 * 描述：${comment}实体类定义
 * 作者：${vars.developer}
 * 邮箱: ${vars.email}
 * 日期:${date?string("yyyy-MM-dd HH:mm:ss")}
 * 版权：${vars.company}
 * </pre>
 */
public class ${class}{

	protected ${pkModel.colType} ${func.convertUnderLine(pkModel.columnName)};

	<#list commonList as col>
	<#assign colName=func.convertUnderLine(col.columnName)>
	<#if func.isExcludeField( colName) >
	protected ${col.colType} ${colName}; //${col.comment}
	</#if>
	</#list>
	
	<#if subtables?exists && subtables?size!=0>
	<#list subtables as subTable>
	<#assign subClass=subTable.variables.class>
	<#assign subClassVar=subTable.variables.classVar>
	<#assign mappingmode=subTable.variables.mappingmode>
	@FieldDefine(title = "${subTable.tabComment}")
	protected List<${subClass}> ${subClassVar}s = new ArrayList<${subClass}>();
	</#list>
	</#if>
	
	public ${pkModel.colType} get${func.convertUnderLine(pkModel.columnName)?cap_first}() {
		return this.${func.convertUnderLine(pkModel.columnName)};
	}
	public void set${func.convertUnderLine(pkModel.columnName)?cap_first}(${pkModel.colType} aValue) {
		this.${func.convertUnderLine(pkModel.columnName)} = aValue;
	}
	<#list commonList as col>
	<#assign colName=func.convertUnderLine(col.columnName)>
	<#if func.isExcludeField( colName) >
	public void set${colName?cap_first}(${col.colType} ${colName}) {
		this.${colName} = ${colName};
	}
	
	/**
	 * 返回 ${col.comment}
	 * @return
	 */
	public ${col.colType} get${colName?cap_first}() {
		return this.${colName};
	}
	</#if>
	</#list>
	
	<#if subtables?exists && subtables?size!=0>
	<#list subtables as subTable>
	<#assign subClass=subTable.variables.class>
	<#assign subClassVar=subTable.variables.classVar>
	public List<${subClass}> get${subClass}s() {
		return ${subClassVar}s;
	}

	public void set${subClass}s(List<${subClass}> in_${subClassVar}) {
		this.${subClassVar}s = in_${subClassVar};
	}
	</#list>
	</#if>
}



