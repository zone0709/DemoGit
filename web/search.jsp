<%-- 
    Document   : search
    Created on : Mar 16, 2018, 9:22:42 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
        Welcome,${sessionScope.USERNAME}
        </font>
        <h1>Search Expired Products</h1>
        <form action="search">
            From Date <input type="text" name="txtFromDate" value="${param.txtFromDate}" placeholder="dd/mm/yyyy" /><br/>
            To Date <input type="text" name="txtToDate" value="${param.txtToDate}" placeholder="dd/mm/yyyy" /><br/>
            <input type="submit" value="Search" />
            <input type="reset" value="Reset" />
        </form><br/>
        <c:set var="FromValue" value="${param.txtFromDate}"/>
        <c:set var="ToValue" value="${param.txtToDate}"/>
        <c:if test="${not empty FromValue or ToValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Expired Date</th>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <th>${counter.count}</th>
                                <th>${dto.productName}</th>
                                <th>${dto.quantity}</th>
                                <th>${dto.expiredDate}</th>
                                <th><input type="checkbox" name="chkAdmin" value="ADMIN"/>
                                    <input type="hidden" name="pk" value="${dto.productid}" />
                                </th>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>

                <input type="submit" value="delete" />
                <input type="hidden" name="txtFromdate" value="${param.txtFromDate}" />
                <input type="hidden" name="txtTodate" value="${param.txtToDate}" />

            </c:if>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                    No record is matched!!
                    </font>
                </h2>
            </c:if>
        </c:if>
    </body>
</html>
