import $ from 'jquery'

class Test {

    init() {
        this.register("common", "123", "0");
        // this.register("admin", "123", "1");
        // this.login("common", "123");
        // this.login("admin", "123");
        // this.clubRegister("csc", "5", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3ZWM0MWQwMTY4MzI0MjFlYWE4NzUzNzQxZTZlMzk2NyIsInN1YiI6IjYiLCJpc3MiOiJzZyIsImlhdCI6MTcxMTAzNDgxMCwiZXhwIjoxNzEyMjQ0NDEwfQ.fDBwk0R5UQ611EjMbgdAH3QIhE5e4RPM4bO3_92PHy8");
        // this.clubAdmit("1", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3ZWM0MWQwMTY4MzI0MjFlYWE4NzUzNzQxZTZlMzk2NyIsInN1YiI6IjYiLCJpc3MiOiJzZyIsImlhdCI6MTcxMTAzNDgxMCwiZXhwIjoxNzEyMjQ0NDEwfQ.fDBwk0R5UQ611EjMbgdAH3QIhE5e4RPM4bO3_92PHy8");
        // this.clubAdmit("1", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhMTlhYzIzZTFkMzg0MWUxOGExMzFiMDJkOTFhZmFiMCIsInN1YiI6IjciLCJpc3MiOiJzZyIsImlhdCI6MTcxMTAzNTAzMiwiZXhwIjoxNzEyMjQ0NjMyfQ.0H2poyB13iVmIrwKKVyREBMTH106c4U9BJGAOfgCgdA");
    }

    register(username, password, role) {
        $.ajax({
            url: "http://127.0.0.1:3000/user/account/register/",
            type: "post",
            data: {
                username: username,
                password: password,
                confirmedPassword: password,
                role: role,
            },
            success(resp) {
                console.log(resp);
            },
            error(resp) {
                console.log(resp);
            }
        });
    }

    login(username, password) {
        $.ajax({
            url: "http://127.0.0.1:3000/user/account/token/",
            type: "post",
            data: {
                username: username,
                password: password,
            },
            success(resp) {
                console.log(resp);
            },
            error(resp) {
                console.log(resp);
            }
        });
    }

    clubRegister(name, president_id, token) {
        $.ajax({
            url: "http://127.0.0.1:3000/club/register/",
            type: "post",
            data: {
                name: name,
                president_id: president_id,
            },
            headers: {
                Authorization: "Bearer " + token,
            },
            success(resp) {
                console.log(resp);
            },
            error(resp) {
                console.log(resp);
            }
        });
    }

    clubAdmit(id, token) {
        $.ajax({
            url: "http://127.0.0.1:3000/club/admit/",
            type: "post",
            data: {
                id: id,
            },
            headers: {
                Authorization: "Bearer " + token,
            },
            success(resp) {
                console.log(resp);
            },
            error(resp) {
                console.log(resp);
            }
        });
    }

}



export const test = new Test();