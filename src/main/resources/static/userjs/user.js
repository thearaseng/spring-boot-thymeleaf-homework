var user={};
var myCurrentPage = 1;
var check = true;
var limit = 2;

$(document).ready(function () {

    /*
    * open add click event handler to object----------------------------------------------------------------------------
    * */
    $(document).on('click', "#id or .class", function () {

    });

    $(document).on('click',"#btDelete" , function(){
        user.deleteUser($(this).data("uuid"));
    });

    $(document).on('click' , "#btDetail" , function(){
        user.detailUser($(this).data("uuid"));
    });

    $(document).on('click' , "#btEdit" , function(){
        user.findAllRole("#userUpdateForm #roleSelect");
        user.findUserUpdate($(this).data("uuid"));
    });

    $(document).on('click' , "#btAdd" , function(){
        user.findAllRole("#userAddModal #roleSelect");
        $('#userAddModal').modal('show');
    });

    $(document).on('click' , "#btUpdateStatus" , function(){
        user.updateUserStatus($(this).data("uuid"), $(this).data("status") );
    });

    /*
     * Submit update user and add user----------------------------------------------------------------------------------
     * */

    // Submit User data to update
    $("#userUpdateForm").submit(function(e){
        e.preventDefault();
        user.submitUserToUpdate();
    });

    // Submit User data to insert
    $("#userAddForm").submit(function(e){
        e.preventDefault();
        user.submitUserToInsert();
    });


    /*
     * Pagination-------------------------------------------------------------------------------------------------------
     * */
    user.setPagination = function(totalPage, currentPage){
        $('#pagination').bootpag({
            total: totalPage,
            page: currentPage,
            maxVisible: 10,
            leaps: true,
            firstLastUse: true,
            first: 'First',
            last: 'Last',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
        }).on("page", function(event, currentPage){
            check = false;
            myCurrentPage = currentPage;
            user.findAllUsers(currentPage,limit);
        });
    };

    /*
     * find all users---------------------------------------------------------------------------------------------------
     * */
    user.findAllUsers = function (page, limit) {
        $.ajax({
            url: "http://localhost:8080/v1/api/user/find-all-user?page=" + page+ "&limit=" + limit,
            type: "GET",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json")
            },
            success: function (data) {
                console.log(data.data);
                if(data.status == true) {
                    $("tbody#user_data").empty();
                    $("#user_tmpl").tmpl({users: data.data}).appendTo("#user_data");
                    if(check) {
                        user.setPagination(data.pagination.total_pages, page);
                        check = false;
                    }
                }
            },
            error: function (data, status, xhr) {
                console.log(data);
            }
        });
    };

    /*
     * detail users-----------------------------------------------------------------------------------------------------
     * */
    user.detailUser = function (data) {
        $.ajax({
            url: "http://localhost:8080/v1/api/user/" + data,
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                $("#username").text(data.data.username);
                $("#email").text(data.data.email);
                $("#dob").text(data.data.dob);
                $("#gender").text(data.data.gender);
                $("#remark").text(data.data.remark);
                $("#status").text(data.data.status);
                for (var i = 0; i < data.data.roles.length; i++)
                    $("#role").text(data.data.roles[i].name);
                $("#userDetailModal").modal('show');
            },
            error:function (data,status,xhr) {
                console.log(data);
            }
        });
    };

    /*
     * find all role add to checkbox------------------------------------------------------------------------------------
     * */
    user.findAllRole = function (type) {
        $.ajax({
            url:  "http://localhost:8080/v1/api/role",
            type: "GET",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function(data) {
                console.log(data);
                $(type).empty();
                for(var i = 0; i<data.data.length; i++){
                    console.log(data.data[i].name);
                    $(type).append("<label>" +
                        "<input class='dd' data-id='"+data.data[i].id+"' type='checkbox'>"+data.data[i].name+"</label>")
                    if(((i+1) % 3)==0)
                        $(type).append("<br>");
                    if(i<data.data.length-1 && ((i+1) % 3)!=0)
                        $(type).append(" | ");
                }

            },
            error:function(data,status,er) {
                console.log(data);
            }
        });
    };

    /*
     * find User detail by uuid to set in update user form--------------------------------------------------------------
     * */
    user.findUserUpdate = function(data){
        $.ajax({
            url:  "http://localhost:8080/v1/api/user/" + data,
            type: "GET",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function(data) {
                console.log(data);
                var roleCheck = [];
                var myRoles = [];
                var role = $("#userUpdateForm #roleSelect").children('label').children('input');
                for(var j = 0; j<data.data.roles.length; j++){
                    for(var i = 0; i<role.length; i++){
                        if(role[i].getAttribute('data-id') == data.data.roles[j].id) {
                            role[i].checked = true;
                        }
                    }
                }

                $("#userUpdateForm #uuid").val(data.data.uuid);
                $("#userUpdateForm #username").val(data.data.username);
                $("#userUpdateForm #email").val(data.data.email);
                $("#userUpdateForm #password").val(data.data.password);
                $("#userUpdateForm #dob").val(data.data.dob);
                $("#userUpdateForm #gender").val(data.data.gender);
                $("#userUpdateForm #remark").val(data.data.remark);
                $("#userUpdateForm #status").val(data.data.status);

                $('#userEditModal').modal('show');
            },
            error:function(data,status,er) {
                console.log(data);
            }
        });
    };

    /*
     * Submit user data to update---------------------------------------------------------------------------------------
     * */
    user.submitUserToUpdate = function(){
        var role = $("#userUpdateForm #roleSelect").children('label').children('input');

        var myRoles = [];
        for(var i = 0; i<role.length; i++){
            if(role[i].checked==true){
                var obj = new Object();
                obj.id = role[i].getAttribute('data-id');
                myRoles[myRoles.length] = obj;
                role[i].checked = false;
            }
        }
        
        cateData = {
            "uuid": $("#userUpdateForm #uuid").val(),
            "username": $("#userUpdateForm #username").val(),
            "email": $("#userUpdateForm #email").val(),
            "password": $("#userUpdateForm #password").val(),
            "dob": $("#userUpdateForm #dob").val(),
            "gender": $("#userUpdateForm #gender").val(),
            "remark": $("#userUpdateForm #remark").val(),
            "status": $("#userUpdateForm #status").val(),
            "roles": myRoles
        };

        swal({   
            title: " User" ,
            text: "Are you sure you want to update this user?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function(){
            $.ajax({
                url:  "http://localhost:8080/v1/api/user/update",
                type: "PUT",
                data: JSON.stringify(cateData),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function(data) {
                    swal(data.message);
                    user.findAllUsers(myCurrentPage, limit);
                    $('#userEditModal').modal('hide');
                },
                error:function(data,status,er) {
                    console.log(data);
                }
            });
        });
    };

    /*
     * Submit user data to insert---------------------------------------------------------------------------------------
     * */
    user.submitUserToInsert = function(){
        var role = $("#userAddModal #roleSelect").children('label').children('input');

        var myRoles = [];
        for(var i = 0; i<role.length; i++){
            if(role[i].checked==true){
                var obj = new Object();
                obj.id = role[i].getAttribute('data-id');
                myRoles[myRoles.length] = obj;
                role[i].checked = false;
            }
        }
        cateData = {
            "username": $("#userAddForm #username").val(),
            "email": $("#userAddForm #email").val(),
            "password": $("#userAddForm #password").val(),
            "dob": $("#userAddForm #dob").val(),
            "gender": $("#userAddForm #gender").val(),
            "remark": $("#userAddForm #remark").val(),
            "roles": myRoles
        };
        swal({
            title: " User" ,
            text: "Are you sure you want to add this user?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        }, function(){
            $.ajax({
                url:  "http://localhost:8080/v1/api/user/insert",
                type: "POST",
                data: JSON.stringify(cateData),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function(data) {
                    swal(data.message);
                    user.findAllUsers(myCurrentPage, limit);
                    $('#userAddModal').modal('hide');
                },
                error:function(data) {
                    console.log(data);
                }
            });
        });
    };

    /*
     * Submit user data to update---------------------------------------------------------------------------------------
     * */
    user.updateUserStatus = function(uuid,status){
        swal({
            title: " User" ,
            text: "Are you sure you want to update this user status?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        }, function(){
            $.ajax({
                url: "http://localhost:8080/v1/api/user/status/"+uuid+"/"+status,
                type: "PUT",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function(data) {
                    swal(data.message);
                    user.findAllUsers(myCurrentPage, limit);
                },
                error:function(data,status,er) {
                    console.log(data);
                }
            });
        });
    };
    /*
     * Delete user by uuid----------------------------------------------------------------------------------------------
     * */
    user.deleteUser = function(data){
        swal({
            title: " User" ,
            text: "Are you sure you want to deleted this user?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function(){
            $.ajax({
                url:  "http://localhost:8080/v1/api/user/delete/"+ data,
                type: "DELETE",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function(data) {
                    swal(data.message);
                    user.findAllUsers(myCurrentPage, limit);
                },
                error:function(data,status,er) {
                    console.log(data);
                }
            });


        });
    };
    /*
     * load all user----------------------------------------------------------------------------------------------
     * */
    user.findAllUsers(myCurrentPage, limit);
});