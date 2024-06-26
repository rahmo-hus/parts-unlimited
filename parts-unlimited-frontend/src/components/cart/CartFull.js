import React, {Component} from 'react';
import {connect} from "react-redux";
import * as $ from 'jquery';
import {addToBasket, changeBasketItem, removeFromBasket} from "../../actions/Basket";
import {Link} from "react-router-dom";
import Price from "../commons/Price";

class CartFull extends Component {

    constructor(props) {
        super(props);
        this.state = {
            basketProducts: {}
        };
    }


    getTotalPrice() {
        if (this.props.basketProducts.length === 0) {
            return '0';
        }
        var total = 0;
        this.props.basketProducts.map(e => total = total + (parseFloat(e.product.price, 10) * e.quantity));
        return total;
    }

    handleIncrease(event) {
        var toChangeProduct = JSON.parse(event.target.id).product;
        var quantity = JSON.parse(event.target.id).quantity + 1;
        $(`.${toChangeProduct.id}`).textContent = quantity;
        this.props.changeBasketItem(toChangeProduct, quantity);
    }

    handleDecrease(event) {
        var toChangeProduct = JSON.parse(event.target.id).product;
        var quantity = JSON.parse(event.target.id).quantity - 1;
        if (quantity > 0) {
            $(`.${toChangeProduct.id}`).textContent = quantity;
            this.props.changeBasketItem(toChangeProduct, quantity);
        }

    }


    renderItem(item) {
        return (
            <div key={item.product.id} className="row m-lg-0 overl bor-r">
                <div className="col-sm-5 col-md-5 col-lg-5 cart-item">
                    <div className="row">
                        <div className="col-sm-3 col-md-3 col-lg-3">
                            <a href="parts-unlimited-frontend/src/components/cart/CartFull" className="cart-img-prev">
                                <img src={item.product.image} alt=""/>
                            </a>
                        </div>
                        <div className="col-sm-9 col-md-9 col-lg-9 p-lg-0">
                            <div className="product-name">
                                <h5>
                                    <a href="parts-unlimited-frontend/src/components/cart/CartFull">{item.product.name}</a>
                                </h5>
                                <span className="price"><Price price={item.product.price}/></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-sm-2 col-md-2 col-lg-2 cart-item">
                    {
                        item.product.quantity && item.product.quantity > 0 ?
                            <p className="color-green">In stock</p>:
                            <p className="color-red">Out of stock</p>
                    }
                </div>


                <div id="containerqtparent" className="col-sm-2 col-md-2 col-lg-2 cart-item ">
                    <div className="containerqt">
                        <button id={JSON.stringify(item)} className="qt-minus"
                              onClick={this.handleDecrease.bind(this)}>-</button>
                        <span id={JSON.stringify(item)} type="number" min="1" className="qt">{item.quantity}</span>
                        <button id={JSON.stringify(item)} className="qt-plus" disabled={item.quantity >= item.product.quantity}
                              onClick={this.handleIncrease.bind(this)}>+</button>
                    </div>
                </div>


                <div className="col-sm-2 col-md-2 col-lg-2 cart-item">
                    <p>
                        <strong>${(Math.round(item.quantity*item.product.price*100)/100).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</strong>
                    </p>
                </div>
                <div className="col-sm-1 col-md-1 col-lg-1 cart-item">
                    <i className="fa fa-remove cart-remove-btn"
                       onClick={() => this.props.removeFromBasket(item.product.id)}/>
                </div>
            </div>

        );

    };

    render() {
        return <div className="wrap-body-inner">
            <div className="hidden-xs">
                <div className="row">
                    <div className="col-lg-6">
                        <ul className="ht-breadcrumb pull-left">
                            <li className="home-act">
                                <a href="parts-unlimited-frontend/src/components/cart/CartFull">
                                    <i className="fa fa-home"/>
                                </a>
                            </li>
                            <li className="home-act">
                                <a href="parts-unlimited-frontend/src/components/cart/CartFull">Shop</a>
                            </li>
                            <li className="active">Cart</li>
                        </ul>
                    </div>
                </div>
            </div>
            <section className="block-cart m-b-lg-50 m-t-lg-30 m-t-xs-0">
                <div>
                    <div className="heading">
                        <h3>Cart</h3>
                    </div>
                    <div className="display-inline-block width-100">

                        {this.props.basketProducts.map((e) => this.renderItem(e))}

                        <div className="clearfix"/>
                        <div className="cart-total" style={{display:'flex', flexDirection:'row', justifyContent:'right'}}>
                            Total: <strong style={{marginTop:2}}><Price price={this.getTotalPrice()}/></strong>
                        </div>
                        <div className="clearfix"/>

                        <Link to={`/purchase`} className="ht-btn ht-btn-default pull-right">
                            Proceed to check out
                        </Link>
                    </div>
                </div>
            </section>
        </div>;
    }


}

function mapStateToProps({basketProducts}) {
    return {basketProducts};
}

export default connect(mapStateToProps, {removeFromBasket, addToBasket, changeBasketItem})(CartFull);

