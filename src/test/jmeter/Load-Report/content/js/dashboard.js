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

    var data = {"OkPercent": 98.0026886883042, "KoPercent": 1.9973113116957941};
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
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.6743326291530631, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.6324257425742574, 500, 1500, "Read Genre"], "isController": false}, {"data": [0.726063829787234, 500, 1500, "Read One Genre"], "isController": false}, {"data": [0.7015503875968992, 500, 1500, "Read Playlist"], "isController": false}, {"data": [0.9268459434822243, 500, 1500, "Read One Artist"], "isController": false}, {"data": [0.7093352192362093, 500, 1500, "Read One Albums"], "isController": false}, {"data": [0.5694996028594123, 500, 1500, "Read Artist"], "isController": false}, {"data": [0.823943661971831, 500, 1500, "Read One Playlist"], "isController": false}, {"data": [0.6400593471810089, 500, 1500, "Read Albums"], "isController": false}]}, function(index, item){
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
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 10414, 208, 1.9973113116957941, 130025.7197042444, 1, 24393984, 38.0, 12921.5, 829177.75, 3043590.900000001, 0.42598669362209896, 24.46881637954322, 0.05234091248975887], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions\/s", "Received", "Sent"], "items": [{"data": ["Read Genre", 404, 6, 1.4851485148514851, 362114.62128712883, 5, 18859598, 462.0, 1432222.0, 2275993.25, 4168203.1499999976, 0.016563044461401978, 0.87965702319105, 0.0020236977039807596], "isController": false}, {"data": ["Read One Genre", 188, 0, 0.0, 289528.0212765957, 2, 24312248, 442.5, 690763.8999999984, 1651534.9999999946, 5348827.539999685, 0.007700467225848928, 0.03588538047046005, 9.700783907563592E-4], "isController": false}, {"data": ["Read Playlist", 129, 0, 0.0, 234611.06201550388, 3, 3961639, 465.0, 1040180.0, 1980794.0, 3867535.8999999966, 0.005288292712002941, 0.10217663213572087, 6.713652857034982E-4], "isController": false}, {"data": ["Read One Artist", 2194, 5, 0.22789425706472197, 42880.8295350957, 1, 24393984, 10.0, 243.5, 2801.25, 1070785.7000000128, 0.08980437504484591, 0.8225859322648863, 0.011374963949314804], "isController": false}, {"data": ["Read One Albums", 707, 9, 1.272984441301273, 288641.84299858555, 2, 24373479, 30.0, 901250.0000000001, 1756579.4000000006, 3107969.8399999994, 0.028947063068649085, 0.6825980389710788, 0.003600230193637427], "isController": false}, {"data": ["Read Artist", 5036, 3, 0.059571088165210485, 15686.262907069124, 4, 15961710, 352.0, 8605.5, 12633.649999999967, 62647.04, 0.20599855858276264, 10.999383103651349, 0.02573448037497628], "isController": false}, {"data": ["Read One Playlist", 71, 0, 0.0, 733585.5211267606, 3, 24350093, 457.0, 1981.7999999999975, 1180452.5999999957, 2.4350093E7, 0.002909261009596341, 0.031672306381816416, 3.750219270182783E-4], "isController": false}, {"data": ["Read Albums", 1685, 185, 10.979228486646884, 411790.224332344, 6, 24390811, 51.0, 1684421.8000000073, 2411297.199999996, 3773303.5799999847, 0.0689712223816815, 10.925621915059976, 0.007614879603875143], "isController": false}]}, function(index, item){
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
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 39, 18.75, 0.3744958709429614], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketException\/Non HTTP response message: Connection reset", 167, 80.28846153846153, 1.6036105242942194], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.NoHttpResponseException\/Non HTTP response message: localhost:8082 failed to respond", 2, 0.9615384615384616, 0.019204916458613407], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 10414, 208, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: Connection reset", 167, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 39, "Non HTTP response code: org.apache.http.NoHttpResponseException\/Non HTTP response message: localhost:8082 failed to respond", 2, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Read Genre", 404, 6, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 6, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Read One Artist", 2194, 5, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 5, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Read One Albums", 707, 9, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 8, "Non HTTP response code: org.apache.http.NoHttpResponseException\/Non HTTP response message: localhost:8082 failed to respond", 1, null, null, null, null, null, null], "isController": false}, {"data": ["Read Artist", 5036, 3, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 3, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": ["Read Albums", 1685, 185, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: Connection reset", 167, "Non HTTP response code: java.net.SocketException\/Non HTTP response message: An established connection was aborted by the software in your host machine", 17, "Non HTTP response code: org.apache.http.NoHttpResponseException\/Non HTTP response message: localhost:8082 failed to respond", 1, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
