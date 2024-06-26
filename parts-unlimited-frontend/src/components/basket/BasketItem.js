import React, {Component} from 'react';
import {connect} from "react-redux";
import {removeFromBasket} from "../../actions/Basket";

class BasketItem extends Component {
    render() {
        return (
            <div className="product-item">
                <div className="row m-lg-0">
                    <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 p-l-lg-0">
                        <a className="product-img">
                            {
                                this.props.item.product.image && <img src={this.props.item.product.image} alt=""/>
                            }
                        </a>
                    </div>
                    <div className="col-xs-7 col-sm-7 col-md-7 col-lg-7 p-lg-0">
                        <div className="product-caption text-left">
                            <h4 className="product-name p-lg-0">
                                <a>{this.props.item.product.name}</a>
                            </h4>
                            <p>
                                {this.props.item.quantity} x <strong>${(Math.round(this.props.item.product.price * 100) / 100).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</strong>
                            </p>
                        </div>
                    </div>
                    <div className="col-xs-1 col-sm-1 col-md-1 col-lg-1 p-lg-0"
                         onClick={() => this.props.removeFromBasket(this.props.item.product.id)}
                    >
                        <i className="fa fa-remove remove-cart-item"/>
                    </div>
                </div>
            </div>
        );
    }

}

export default connect(null, {removeFromBasket})(BasketItem);
