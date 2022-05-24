import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import Basket from './Basket';
import {connect} from "react-redux";
import {logout, whoAmI} from "../actions/Auth";
import {turnOffHomeFilter} from '../actions/Car';

// import {connect} from "react-redux";

class Header extends Component {

    componentDidMount() {
        this.props.dispatch(whoAmI());
    }

    logout = () => {
        this.props.dispatch(logout());
    }

    render() {
        const {loggedIn, user} = this.props;
        return (
            <header id="wrap-header" className="color-inher">
                <div className="top-header">
                    <div className="container">
                        <div className="row">
                            <div className="col-sm-6 col-md-6 col-lg-6 hidden-xs">
                                <p className="f-14">
                                    <i className="fa fa-map-marker m-r-lg-5"/>
                                    <strong>PARTS UNLIMITED</strong> - Patre 5, Banja Luka, Bosnia and Herzegovina
                                </p>
                            </div>
                            <div className="col-sm-6 col-md-6 col-lg-6">
                                <ul className="pull-right">
                                    {!user ?
                                        <Link to="/signup">
                                            <li>
                                                <a className="icon-1">
                                                    <i className="fa fa-user fa-mouse-pointer"/>
                                                    <span>Sign up</span>
                                                </a>
                                            </li>
                                        </Link>
                                        :
                                        <li className>
                                            <a className="icon-1">
                                                <i className="fa fa-user"/>
                                                <span>{user.username}</span>
                                            </a>
                                        </li>
                                    }
                                    <li>
                                        <Link to={'/history'}>
                                            <span>History</span>
                                        </Link>
                                    </li>
                                    {
                                        user && user.roles[0] === 'ROLE_CUSTOMER' &&
                                        <li className="cart-icon">
                                            <Link to={"/wishlist"}>
                                                <i className="fa fa-heart"/>
                                                <span className="badge">{this.props.wishlistproducts.length}</span>
                                            </Link>
                                        </li>
                                    }
                                    {
                                        user && user.roles[0] === 'ROLE_CUSTOMER' &&
                                        <Basket/>
                                    }
                                    {user &&
                                        <li>
                                            <a style={{cursor: 'pointer'}} onClick={this.logout}>
                                                <i className="fa fa-sign-out"></i>
                                            </a>
                                        </li>
                                    }
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="menu-bg">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-3 col-lg-3">
                                <a className="logo">
                                    <img src={`${process.env.PUBLIC_URL}/images/logo.png`} alt="logo"/>
                                </a>
                            </div>
                            <div className="col-md-9 col-lg-9">
                                <div className="hotline">
                                    <span className="m-r-lg-10">Need support? Call us:</span>
                                    <a href="tel:01686813774">
                                        <i className="fa fa-phone"/>+387 51 123 456
                                    </a>
                                </div>
                                <div className="clearfix"/>

                                <div className="main-menu">
                                    <div className="container1">
                                        <nav className="navbar navbar-default menu">
                                            <div className="container1-fluid">
                                                <div className="navbar-header">
                                                    <button
                                                        type="button"
                                                        className="navbar-toggle collapsed"
                                                        data-toggle="collapse"
                                                        data-target="#bs-example-navbar-collapse-1"
                                                        aria-expanded="false">
                                                        <span className="sr-only">Toggle navigation</span>
                                                        <span className="icon-bar"/>
                                                        <span className="icon-bar"/>
                                                        <span className="icon-bar"/>
                                                    </button>
                                                </div>
                                                <div
                                                    className="collapse navbar-collapse"
                                                    id="bs-example-navbar-collapse-1"
                                                >
                                                    <ul className="nav navbar-nav">
                                                        <li className="dropdown"
                                                            onClick={() => turnOffHomeFilter()}>
                                                            <Link to={"/home"} className="dropdown-toggle">
                                                                HOME
                                                            </Link>
                                                        </li>
                                                        <li className="dropdown"
                                                            onClick={() => turnOffHomeFilter()}>
                                                            <Link to={"/cars"} className="dropdown-toggle">
                                                                CARS
                                                            </Link>
                                                        </li>
                                                        <li className="dropdown">
                                                            <Link to={"/products"} className="dropdown-toggle">
                                                                PARTS
                                                            </Link>
                                                        </li>
                                                        {
                                                            user && user.roles[0] === 'ROLE_WAREHOUSE' &&
                                                            <li className="dropdown">
                                                                <Link to={'cars/sell'} className="dropdown-toggle">
                                                                    ADD A CAR
                                                                </Link>
                                                            </li>
                                                        }
                                                        {
                                                            user && user.roles[0] === 'ROLE_WAREHOUSE' &&
                                                            <li className="dropdown">
                                                                <Link to={'products/sell'} className="dropdown-toggle">
                                                                    ADD A PART
                                                                </Link>
                                                            </li>
                                                        }
                                                    </ul>
                                                </div>
                                            </div>
                                        </nav>

                                        <div className="search-box">
                                            <i className="fa fa-search"/>
                                            <form>
                                                <input
                                                    type="text"
                                                    name="search-txt"
                                                    placeholder="Search..."
                                                    className="search-txt form-item"
                                                />
                                                <button
                                                    type="submit"
                                                    name="submit"
                                                    className="search-btn btn-1"
                                                >
                                                    <i className="fa fa-search"/>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        );
    }
}

function mapStateToProps(state) {
    const {users, authentication, wishlistproducts} = state;
    const {user, loggedIn} = authentication;
    return {
        user,
        loggedIn,
        users,
        wishlistproducts
    };
}

export default connect(mapStateToProps)(Header);

