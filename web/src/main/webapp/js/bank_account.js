(function() {
  var dataTab = null;

  function openEditDialog(id, bic, iban) {
    $("#editAccount").dialog({
      buttons: {
        "Save": function() {
          $("#operation_status").html("<div id='status' class='alert-box notice'><span>loading: </span>Saving in progress</div>");
          $.getJSON("" + ctx + "/bank_accounts/save?id=" +$("#idText").val()+"&bic="+$("#bicText").val()+"&iban="+$("#ibanText").val(), function(d) {
            refresh();
            $("#operation_status").html("");
          });
        }
      },
      open: function() {
        $("#idText").val(id);
        $("#bicText").val(bic);
        $("#ibanText").val(iban);
      },
      close: function() {
        $("#idText").val("");
        $("#bicText").val("");
        $("#ibanText").val("");
      }
    });
  }

  function refresh() {
    $("#bank_accounts_table  > tbody").html("");
    $("#operation_status").html("<div id='status' class='alert-box notice'><span>loading: </span>Loading accounts in progress</div>");
    return $.getJSON("" + ctx + "/bank_accounts/list/", function(data) {
      if (dataTab !== null) {
        dataTab.destroy();
      }
      dataTab = $("#bank_accounts_table").DataTable({
        dom: 'Bfrtip',
        data: data,
        buttons: [
          {
            text: "Add",
            action: function ( e, dt, node, config ) {
              alert( 'Button activated' );
            }
          }
        ],
        columns: [
          {
            render: function(data, type, row) {
              return "";
            },
            visible: false
          }, {
            data: "iban",
            title: "IBAN"
          }, {
            data: "bic",
            title: "BIC"
          }, {
            className:      'edit',
            orderable:      false,
            data:           null,
            defaultContent: ''
          }, {
            className:      'remove',
            orderable:      false,
            data:           null,
            defaultContent: ''
          }
        ]
      });
      $('#bank_accounts_table tbody').on('click', 'td.edit', function () {
        var data = dataTab.row( $(this).parents('tr') ).data();
        openEditDialog(data.id,data.bic, data.iban);
      });
      $('#bank_accounts_table tbody').on('click', 'td.remove', function () {
        var data = dataTab.row( $(this).parents('tr') ).data();
        $("#operation_status").html("<div id='status' class='alert-box notice'><span>loading: </span>Removing in progress</div>");
        $.getJSON("" + ctx + "/bank_accounts/remove?id=" +data.id, function(d) {
          refresh();
          $("#operation_status").html("");
        });
      });
      $("#operation_status").html("");
    });
  }

  refresh();
}).call(this);
