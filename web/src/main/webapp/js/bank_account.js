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
          $( this ).dialog( "close" );
        },
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
    return $.getJSON("" + ctx + "/bank_accounts/list/", function(data) {
      if (dataTab !== null) {
        dataTab.destroy();
      }
      dataTab = $("#bank_accounts_table").DataTable({
        dom: 'Bfrtip',
        data: data,
        buttons: [
          {
            className: 'add',
            action: function ( e, dt, node, config ) {
              openEditDialog("","","");
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
          if (d.hasErrors) {
            $("#operation_status").html("<div id='status' class='alert-box error'><span>error: </span>"+ d.errors+"</div>");
          } else {
            $("#operation_status").html("<div id='status' class='alert-box success'><span>successed: </span>Removed</div>");
          }
          refresh();
        });
      });
    });
  }

  $("#editAccount").validate({
    //errorLabelContainer: $("#add_statement_form div.error"),
    wrapper: 'li',
    rules: {
      bicText: {
        required: true
      },
      ibanText: {
        required: true
      }
    },
    messages: {
      bicText: {
        required: "Телефон не может быть пустым"
      },
      ibanText: {
        required: "Количество постановок не может быть пустым"
      }
    }
  });

  refresh();
}).call(this);
