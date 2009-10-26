/*
 * Copyright (c) 2009 - 2009 Open Source Strategies, Inc.
 *
 * Opentaps is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Opentaps is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Opentaps.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opentaps.gwt.common.client.listviews;

import org.opentaps.gwt.common.client.UtilUi;
import org.opentaps.gwt.common.client.lookup.configuration.OrderLookupConfiguration;

import com.gwtext.client.core.SortDir;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.grid.ColumnConfig;

/**
 * Class for the Find order form + list view pattern.
 */
public class OrderListView extends EntityListView {

    private static final String MODULE = OrderListView.class.getName();
 
    /**
     * Default constructor.
     */
    public OrderListView() {
        super();
    }

    /**
     * Constructor giving a title for this list view, which is displayed in the UI.
     * @param title the title label for this list view.
     */
    public OrderListView(String title) {
        super(title);
    }

    /**
     * Placeholder to remind extended classes that on of the init methods must be called.
     */
    public void init() {
        init(OrderLookupConfiguration.URL_FIND_ORDERS, "/crmsfa/control/orderview?orderId={0}", UtilUi.MSG.orderOrderId());
    }

    /**
     * Configures the list columns and interaction with the server request that populates it.
     * Constructs the column model and JSON reader for the list with the default columns for Party and extra columns, as well as a link for a view page.
     * @param entityFindUrl the URL of the request to populate the list
     * @param entityViewUrl the URL linking to the entity view page with a placeholder for the ID. The ID column will use it to provide a link to the view page for each record. For example <code>/crmsfa/control/viewContact?partyId={0}</code>. This is optional, if <code>null</code> then no link will be provided
     * @param idLabel the label of the ID column, which depends of the entity that is listed
     */
    protected void init(String entityFindUrl, String entityViewUrl, String idLabel) {
        StringFieldDef idDefinition = new StringFieldDef(OrderLookupConfiguration.INOUT_ORDER_ID);

        ColumnConfig columnOrderDate = makeColumn(UtilUi.MSG.orderOrderDate(), new StringFieldDef(OrderLookupConfiguration.OUT_ORDER_DATE_STRING));
        columnOrderDate.setWidth(100);

        ColumnConfig columnOrderNameId = makeLinkColumn(UtilUi.MSG.crmOrderNameID(), idDefinition, new StringFieldDef(OrderLookupConfiguration.OUT_ORDER_NAME_ID), entityViewUrl, true);
        columnOrderNameId.setWidth(150);

        ColumnConfig columnCorrespondingPoId = makeColumn(UtilUi.MSG.opentapsPONumber(), new StringFieldDef(OrderLookupConfiguration.INOUT_CORRESPONDING_PO_ID));
        columnCorrespondingPoId.setWidth(80);

        ColumnConfig columnCustomer = makeColumn(UtilUi.MSG.crmCustomer(), new StringFieldDef(OrderLookupConfiguration.OUT_CUSTOMER_NAME));
        columnCustomer.setWidth(120);

        ColumnConfig columnStatus = makeColumn(UtilUi.MSG.commonStatus(), new StringFieldDef(OrderLookupConfiguration.OUT_STATUS_DESCRIPTION));
        columnStatus.setWidth(100);

        ColumnConfig columnShipByDate = makeColumn(UtilUi.MSG.orderShipBeforeDate(), new StringFieldDef(OrderLookupConfiguration.OUT_SHIP_BY_DATE_STRING));
        columnShipByDate.setWidth(100);

        ColumnConfig columnAmount = makeCurrencyColumn(UtilUi.MSG.orderAmount(), new StringFieldDef(OrderLookupConfiguration.OUT_CURRENCY_UOM), new StringFieldDef(OrderLookupConfiguration.OUT_GRAND_TOTAL));
        columnAmount.setWidth(100);

        configure(entityFindUrl, OrderLookupConfiguration.INOUT_ORDER_DATE, SortDir.DESC);
    }


    /**
     * Filters the records of the list by showing only those belonging to the user making the request.
     * @param viewPref a <code>Boolean</code> value
     */
    public void filterMyOrTeamParties(String viewPref) {
        setFilter(OrderLookupConfiguration.IN_RESPONSIBILTY, viewPref);
    }

    /**
     * Filters the records of the list by order name matching the given order name.
     * @param orderName a <code>String</code> value
     */
    public void filterByOrderName(String orderName) {
        setFilter(OrderLookupConfiguration.INOUT_ORDER_NAME, orderName);
    }

    /**
     * Filters the records of the list by order Id matching the given orderId.
     * @param orderId a <code>String</code> value
     */
    public void filterByOrderId(String orderId) {
        setFilter(OrderLookupConfiguration.INOUT_ORDER_ID, orderId);
    }

    /**
     * Filters the records of the list by order Id matching the given orderId.
     * @param externalId a <code>String</code> value
     */
    public void filterByExternalId(String externalId) {
        setFilter(OrderLookupConfiguration.IN_EXTERNAL_ID, externalId);
    }

    /**
     * Filters the records of the list by customer Id matching the given orderId.
     * @param customerId a <code>String</code> value
     */
    public void filterByCustomerId(String customerId) {
        setFilter(OrderLookupConfiguration.INOUT_PARTY_ID, customerId);
    }

    /**
     * Filters the records of the list by product store Id matching the given productStoreId.
     * @param productStoreId a <code>String</code> value
     */
    public void filterByProductStoreId(String productStoreId) {
        setFilter(OrderLookupConfiguration.IN_PRDOUCT_STORE_ID, productStoreId);
    }

    /**
     * Filters the records of the list by order status Id matching the given statusId.
     * @param statusId a <code>String</code> value
     */
    public void filterByStatusId(String statusId) {
        setFilter(OrderLookupConfiguration.INOUT_STATUS_ID, statusId);
    }

    /**
     * Filters the records of the list by corresponding Po Id matching the given correspondingPoId.
     * @param correspondingPoId a <code>String</code> value
     */
    public void filterByCorrespondingPoId(String correspondingPoId) {
        setFilter(OrderLookupConfiguration.INOUT_CORRESPONDING_PO_ID, correspondingPoId);
    }

    /**
     * Filters the records of the list by from Date matching the given fromDate.
     * @param fromDate a <code>String</code> value
     */
    public void filterByFromDate(String fromDate) {
        setFilter(OrderLookupConfiguration.IN_FROM_DATE, fromDate);
    }


    /**
     * Filters the records of the list by thru Date matching the given thruDate.
     * @param thruDate a <code>String</code> value
     */
    public void filterByThruDate(String thruDate) {
        setFilter(OrderLookupConfiguration.IN_THRU_DATE, thruDate);
    }

    /**
     * Filters the records of the list by created by matching the given createdBy.
     * @param createdBy a <code>String</code> value
     */
    public void filterByCreatedBy(String createdBy) {
        setFilter(OrderLookupConfiguration.IN_CREATED_BY, createdBy);
    }

    /**
     * Filters the records of the list by lot Id matching the given lotId.
     * @param lotId a <code>String</code> value
     */
    public void filterByLotId(String lotId) {
        setFilter(OrderLookupConfiguration.IN_LOT_ID, lotId);
    }

    /**
     * Filters the records of the list by serial number matching the given serialNumber.
     * @param serialNumber a <code>String</code> value
     */
    public void filterBySerialNumber(String serialNumber) {
        setFilter(OrderLookupConfiguration.IN_SERIAL_NUMBER, serialNumber);
    }

}
