/*
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/* This file has been modified by Open Source Strategies, Inc. */

import org.ofbiz.entity.*;
import org.ofbiz.base.util.*;
import org.ofbiz.entity.*;
import org.ofbiz.entity.condition.*;
import org.ofbiz.entity.util.*;

projectId = parameters.projectId;
entryExprs =
    EntityCondition.makeCondition([
        EntityCondition.makeCondition("projectId", EntityOperator.EQUALS, projectId),
        EntityCondition.makeCondition("invoiceId", EntityOperator.NOT_EQUAL, null),
        ], EntityOperator.AND);
orderBy = ["-fromDate"];
// check if latest invoice generated is still in process so allow re-generation to correct errors
entryIterator = delegator.find("ProjectPhaseTaskAndTimeEntryTimeSheet", entryExprs, null, null, orderBy, null);
while (entryItem = entryIterator.next()) {
    invoice = entryItem.getRelatedOne("Invoice");
    if (invoice.getString("statusId").equals("INVOICE_IN_PROCESS")) {
        context.partyIdFrom = invoice.partyIdFrom;
        context.partyId = invoice.partyId;
        context.invoiceId = invoice.invoiceId;
        break;
        }
    }
entryIterator.close();
//start of this month
context.thruDate = UtilDateTime.getMonthStart(UtilDateTime.nowTimestamp());
