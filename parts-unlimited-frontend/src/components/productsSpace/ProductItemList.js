import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import {
  fetchSingleProduct,
} from "../../actions/Product";

import {addToBasket} from "../../actions/Basket";
import {addToWishList, removeFromWishList} from "../../actions/Wishlist";

class ProductItemList extends Component {

  constructor(props) {
    super(props);
    this.state = { isInWishList: false, product: props.product, isHovered:false };
    this.componentDidMount = this.componentDidMount.bind(this);
  }

  ToggleProductWishlist(event) {

    var product = JSON.parse(event.target.id);
    if (!this.state.isInWishList) {
      this.props.addToWishList(product);
      this.setState({ isInWishList: true });
    } else {
      this.props.removeFromWishList(product);
      this.setState({ isInWishList: false });
    }

  }

  componentDidMount() {
    var product = this.props.product;

    if (
      this.props.wishlistproducts.filter(function(e) {
        return e.id === product.id;
      }).length === 1
    ) {
      this.setState({ isInWishList: true });
    }
  }

  mouseHover(event){
    this.setState({isHovered:true});  
  }

  mouseUnHover(event){
      this.setState({isHovered:false});  
  }

  render() {
    var heartStyle = {};
        if(!this.state.isHovered){
            heartStyle = {
                color: '#d11717',
                fontWeight: 'bold'
            };
        }
    const {product} = this.props;
    return (
      <div className="product-item hover-img">
        <div className="row">
          <div className="col-sm-6 col-md-5 col-lg-4">
            <a className="product-img">
              <img
                src={product
                  .image}
                style={{ width: "197.5px", height: "197.5px" }}
                alt=""
              />
            </a>
          </div>
          <div className="col-sm-6 col-md-7 col-lg-8 static-position">
            <div className="product-caption">
              <h4 className="product-name">
                <a>{product.name}</a>
              </h4>
              <ul className="rating">
                <li className="active">
                  <i className="fa fa-star" />
                </li>
                <li className="active">
                  <i className="fa fa-star" />
                </li>
                <li className="active">
                  <i className="fa fa-star" />
                </li>
                <li>
                  <i className="fa fa-star" />
                </li>
                <li>
                  <i className="fa fa-star" />
                </li>
              </ul>
              <div className="product-price-group">
                <span className="product-price">
                  ${product.price}
                </span>
              </div>
              <p className="product-txt">
                Nunc facilisis sagittis ullamcorper. Proin lectus ipsum
              </p>
              <a
                className="ht-btn ht-btn-default"
                onClick={() => {
                  this.props.addToBasket(product, 1);
                }}
              >
                Add to cart
              </a>
              <ul className="absolute-caption">
                <li>
                    <i id={JSON.stringify(product)} className={this.state.isInWishList ? "fa fa-heart" : "fa fa-heart-o"} 
                    style={heartStyle} 
                    onClick={this.ToggleProductWishlist.bind(this)}
                    onMouseEnter={this.mouseHover.bind(this)}
                    onMouseLeave={this.mouseUnHover.bind(this)}
                    />
                </li>
                <li>
                  <i
                    className="fa fa-shopping-basket"
                    onClick={() => {
                      this.props.addToBasket(product, 1);
                    }}
                  />
                </li>
                <li onClick={()=>this.props.fetchSingleProduct(product)}>
                  <Link to={`/products/singleproduct/${product.id}`}>
                    <i className="fa fa-search" />
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

function mapStateToProps({ wishlistproducts }) {
  return { wishlistproducts };
}

export default connect(mapStateToProps, {fetchSingleProduct, addToBasket, addToWishList, removeFromWishList })(ProductItemList);
