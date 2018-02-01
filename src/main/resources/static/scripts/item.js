function AppViewModel() {
    // this.firstName = "Bert";
    // this.lastName = "Bertington";

    $.getJSON("/item", function(data) {
        console.log(data);
        // Now use this data to update your view models,
        // and Knockout will update your UI automatically
    })


    // var $form = $('#itemAddForm');
    // $form.on('submit', function(e) {
    //     e.preventDefault();
    //     $.ajax({
    //         url: $form.attr('action'),
    //         type: 'post',
    //         data: $form.serialize(),
    //         success: function(response) {
    //             // if the response contains any errors, replace the form
    //             if ($(response).find('.has-error').length) {
    //                 $form.replaceWith(response);
    //             } else {
    //                 // in this case we can actually replace the form
    //                 // with the response as well, unless we want to
    //                 // show the success message a different way
    //             }
    //         }
    //     });
    // });





    // $.ajax({
    //     type: "POST",
    //     url: "api/item/add",
    //     data: "{'disciplineRecordId': '" + 38 + "'}",
    //     contentType: "application/json; charset=utf-8",
    //     dataType: "json",
    //     success: function (result) {         //Database returns records, but the binding does not work.
    //         self.programOutcomeText(result.d[0].disciplineOutcome);
    //     },
    //     error: function (XMLHttpRequest, textStatus, errorThrown) {
    //         alert(textStatus);
    //     }
    // });
}

ko.applyBindings(new AppViewModel());