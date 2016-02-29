<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/header.jsp"/>
<table id="bank_accounts_table">
    <thead>
    </thead>
    <tfoot>
    <tr>
        <th></th><th></th><th></th><th></th>
    </tr>
    </tfoot>
    <tbody></tbody>
</table>
<button id="addAccount">Add account</button>
<div style="display: none" >
    <div id="editAccount" title="Account Edit">
        <form>
            <table>
                <tr>
                    <td><input type="text" id="idText" name="id" readonly="readonly" hidden="hidden" /></td>
                </tr>
                <tr>
                    <td><label for="bicText">BIC</label></td>
                    <td><input type="text" id="bicText" name="v" /></td>
                </tr>
                <tr>
                    <td><label for="ibanText">IBAN</label></td>
                    <td><input type="text" id="ibanText" name="iban" /></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
