import React, {Component} from "react";
import {Link} from "react-router-dom";

import BasketItem from './BasketItem';
import {connect} from "react-redux";


class Basket extends Component {
    constructor(props) {
        super(props);
        this.state = {basketProducts: {}};
    }

    renderItem(item) {
        console.log(item)
        return (
            <BasketItem key={item.product.id} item={item}/>
        );
    }

    getTotalPrice() {
        if (this.props.basketProducts.length === 0) {
            return '0';
        }
        var total = 0;
        this.props.basketProducts.map(e => total = total + (parseInt(e.product.price, 10) * e.quantity));
        return total;
    }

    render() {
        return (
            <li className="cart-icon">
                <a>
                    <i className="fa fa-shopping-basket"/>
                    <span className="badge">
              {this.props.basketProducts.length}
          </span>
                </a>
                <ul className="cart-dropdown">
                    <li className="bg-white bg1-gray-15 color-inher">
                        {this.props.basketProducts.map(e => this.renderItem(e))}
                        <div className="p-t-lg-15 p-b-lg-10">
                            Total : <strong className="pull-right price">$
                            {this.getTotalPrice()}
                        </strong>
                        </div>
                        <div className="clearfix"/>
                        <Link to={"/cart"} className="ht-btn">
                            Check out
                        </Link>
                        <Link to={"/cart"} className="ht-btn pull-right">
                            View Cart
                        </Link>
                    </li>
                </ul>
            </li>
        );
    }
}

function mapStateToProps({basketProducts}) {
    return {basketProducts};
}

export default connect(mapStateToProps)(Basket);
