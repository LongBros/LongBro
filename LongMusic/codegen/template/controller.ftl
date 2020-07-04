<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign comment=model.tabComment>
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

package ${system}.${domain}.${package}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import ${system}.${domain}.${package}.bean.${class};
import ${system}.${domain}.${package}.service.${class}Service;
/**
 * ${comment}控制器
 * @author ${vars.developer}
 * @date ${date?string("yyyy-MM-dd HH:mm:ss")}
 * @copyright ${vars.company}
 */
@Controller
@RequestMapping("sky/${classVar}/")
public class ${class}Controller{
    @Autowired
    ${class}Service ${classVar}Service;
    
    @RequestMapping("create")
    public void create(${class} bean) {
		${classVar}Service.create(bean);
	}
}
