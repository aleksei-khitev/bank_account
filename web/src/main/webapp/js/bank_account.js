(function() {
  var dataTab = null;
  function refresh() {
    $("#bank_accounts_table  > tbody").html("");
    $("#operation_status").html("<div id='status' class='alert-box notice'><span>loading: </span>Идёт загрузка абонементов</div>");
    return $.getJSON("" + ctx + "/bank_accounts/list/", function(data) {
      if (dataTab !== null) {
        dataTab.destroy();
      }
      dataTab = $("#bank_accounts_table").DataTable({
        dom: 'T<"clear">lfrtip',
        "data": data,
        "columns": [
          {
            "render": function(data, type, row) {
              return "";
            },
            "visible": false
          }, {
            "data": "iban",
            "title": "IBAN"
          }, {
            "data": "bic",
            "title": "BIC"
          }, {
            "className":      'details-control',
            "orderable":      false,
            "data":           null,
            "defaultContent": ''
          }
        ]
      });
      $('#bank_accounts_table tbody').on('click', 'td.details-control', function () {
        
      });
    });
  }

  refresh();
}).call(this);
