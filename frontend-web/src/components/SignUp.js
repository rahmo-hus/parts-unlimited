import React, {Component} from "react";

export default class SignUp extends Component {
    render() {
        return (
            <div className="container bg-white width-100 border-radius-1 d-flex justify-content-center">
                <form className="col-md-1 w-50-percent container p-b-lg-20">
                    <h3 className="text-center">Register</h3>

                    <div className="form-group">
                        <label>First name</label>
                        <input type="text" className="form-control" placeholder="John"/>
                    </div>

                    <div className="form-group">
                        <label>Last name</label>
                        <input type="text" className="form-control" placeholder="Doe"/>
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" className="form-control" placeholder="john.doe@example.com"/>
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="********"/>
                    </div>

                    <div className="form-group">
                        <label>Repeat password</label>
                        <input type="password" className="form-control" placeholder="********"/>
                    </div>

                    <button type="submit" className="btn btn-dark btn-lg btn-block">Register</button>
                </form>
                <form className="col-md-1 w-50-percent">
                    <h3 className="text-center">LOGIN</h3>
                    <div className="form-group">
                        <label>E-mail</label>
                        <input type="email" className="form-control" placeholder="user@example.com"/>
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="********"/>
                    </div>

                    <button type="submit" className="btn btn-default btn-lg btn-block">Login</button>
                </form>
            </div>
        );
    }
}