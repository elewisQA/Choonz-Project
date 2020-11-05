/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 45.006016847172084, "KoPercent": 54.993983152827916};
    var dataset = [
        {
            "label" : "KO",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "OK",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.3561973525872443, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.24615384615384617, 500, 1500, "Read Genre"], "isController": false}, {"data": [0.0, 500, 1500, "Delete playlist"], "isController": false}, {"data": [0.8645833333333334, 500, 1500, "Create User"], "isController": false}, {"data": [0.625, 500, 1500, "Add Track"], "isController": false}, {"data": [0.8181818181818182, 500, 1500, "Read Playlist"], "isController": false}, {"data": [0.2608695652173913, 500, 1500, "Read Artist"], "isController": false}, {"data": [0.0, 500, 1500, "Remove Track"], "isController": false}, {"data": [0.0, 500, 1500, "Create Playlist"], "isController": false}, {"data": [0.245, 500, 1500, "Read Albums"], "isController": false}, {"data": [0.9111111111111111, 500, 1500, "User Login"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 831, 457, 54.993983152827916, 20334.746089049353, 2, 43419, 30091.0, 37971.8, 39564.4, 40916.35999999999, 6.690821256038648, 563.8941019399155, 1.3993700306964574], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions\/s", "Received", "Sent"], "items": [{"data": ["Read Genre", 195, 120, 61.53846153846154, 23416.38974358976, 5, 39909, 30099.0, 33250.8, 34511.99999999999, 39896.52, 1.5758721846437316, 111.1763305008041, 0.19544508540015032], "isController": false}, {"data": ["Delete playlist", 10, 8, 80.0, 8931.6, 4, 30093, 14.0, 30039.2, 30093.0, 30093.0, 0.19983214100155872, 0.305914906478558, 0.2486778293733264], "isController": false}, {"data": ["Create User", 48, 5, 10.416666666666666, 3684.520833333333, 2, 40690, 4.0, 30040.2, 36667.34999999999, 40690.0, 0.42164441321152496, 0.5730870904339423, 0.11529339423752635], "isController": false}, {"data": ["Add Track", 28, 10, 35.714285714285715, 8560.07142857143, 10, 38583, 202.5, 33828.90000000001, 37949.85, 38583.0, 0.34819374494808186, 176.3420857815706, 0.3234927796431014], "isController": false}, {"data": ["Read Playlist", 55, 10, 18.181818181818183, 6193.290909090909, 4, 39844, 9.0, 32322.399999999998, 38332.99999999999, 39844.0, 0.4826890166308307, 109.8100286185221, 0.06127887906446093], "isController": false}, {"data": ["Read Artist", 207, 136, 65.70048309178743, 24674.275362318833, 7, 43419, 33686.0, 38824.6, 40034.6, 43177.99999999999, 1.8164108774054282, 67.8974485377673, 0.22705135967567852], "isController": false}, {"data": ["Remove Track", 22, 22, 100.0, 34441.36363636363, 4, 43227, 36192.0, 40906.0, 42911.7, 43227.0, 0.28356362136522995, 2.8508792687603113, 0.1899277114160136], "isController": false}, {"data": ["Create Playlist", 21, 17, 80.95238095238095, 30697.04761904762, 4, 36907, 30166.0, 36513.8, 36880.1, 36907.0, 0.44274841348485167, 3.570791485262803, 0.3857783214037233], "isController": false}, {"data": ["Read Albums", 200, 126, 63.0, 24338.820000000018, 7, 39942, 30154.0, 39535.1, 39766.95, 39893.93, 2.2146186980256672, 236.24708812244629, 0.27466462368091776], "isController": false}, {"data": ["User Login", 45, 3, 6.666666666666667, 2395.6666666666665, 3, 37261, 5.0, 1450.799999999995, 34589.59999999998, 37261.0, 0.5145444566410537, 0.4771260869751646, 0.08391496509673435], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["400", 5, 1.0940919037199124, 0.601684717208183], "isController": false}, {"data": ["500", 446, 97.59299781181619, 53.67027677496991], "isController": false}, {"data": ["404", 6, 1.312910284463895, 0.7220216606498195], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 831, 457, "500", 446, "404", 6, "400", 5, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Read Genre", 195, 120, "500", 120, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Delete playlist", 10, 8, "404", 6, "400", 1, "500", 1, null, null, null, null], "isController": false}, {"data": ["Create User", 48, 5, "500", 5, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Add Track", 28, 10, "500", 8, "400", 2, null, null, null, null, null, null], "isController": false}, {"data": ["Read Playlist", 55, 10, "500", 10, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Read Artist", 207, 136, "500", 136, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Remove Track", 22, 22, "500", 21, "400", 1, null, null, null, null, null, null], "isController": false}, {"data": ["Create Playlist", 21, 17, "500", 16, "400", 1, null, null, null, null, null, null], "isController": false}, {"data": ["Read Albums", 200, 126, "500", 126, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["User Login", 45, 3, "500", 3, null, null, null, null, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
