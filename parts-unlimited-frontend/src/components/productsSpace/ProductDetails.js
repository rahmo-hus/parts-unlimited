import React from 'react';
import {connect} from 'react-redux';
import {addToBasket} from '../../actions/Basket';
import {Link} from 'react-router-dom';
import {fetchProducts, fetchSingleProduct} from "../../actions/Product";
import {whoAmI} from "../../actions/Auth";

class ProductDetails extends React.Component {

    componentDidMount() {
        const id = window.location.href.charAt(window.location.href.length - 1);
        this.props.fetchSingleProduct(id);
        this.props.fetchProducts(this.props.currentProduct.category);
        this.props.whoAmI();
    }


    render() {

        const {
            id,
            serial,
            name,
            productionDate,
            brand,
            type,
            code,
            manufacturer,
            category,
            image,
            description,
            price,
            cars
        } = this.props.currentProduct;

        let distinctProducts = Object.assign(this.props.products);
        let index = distinctProducts.indexOf(distinctProducts.find(e => e.id === id));
        if (index !== -1) {
            distinctProducts.splice(index, 1);
        }
        distinctProducts = distinctProducts.slice(0, 3);

        return (
            <div className="wrap-body-inner">
                <div className=" col-sm-8 col-md-9 col-lg-9">
                    <Link to={"/products"} style={{width: 25}}
                          className="col-sm-1 col-md-1 col-lg-1 p-lg-0 ht-btn ht-btn-default pull-right">
                        <i style={{marginLeft: 7}} className="fa fa-remove center"/>
                    </Link>
                    <div className="product-list product_detail p-lg-30 p-xs-15 bg-gray-fa bg1-gray-15 m-b-lg-50">
                        <div className="row">
                            <div className="image-zoom col-md-6 col-lg-6">
                                <div className="product-img-lg p-lg-10 m-b-xs-30 text-center">
                                    <a>
                                        {
                                            image && <img src={image} alt=""/>
                                        }
                                    </a>
                                </div>
                            </div>
                            <div className="col-md-6 col-lg-6">
                                <h3 className="product-name">{name}</h3>
                                <div className="product_para">
                                    <ul className="rating pull-left m-r-lg-20">
                                        <li className="active"><i className="fa fa-star"></i></li>
                                        <li className="active"><i className="fa fa-star"></i></li>
                                        <li className="active"><i className="fa fa-star"></i></li>
                                        <li><i className="fa fa-star"></i></li>
                                        <li><i className="fa fa-star"></i></li>
                                    </ul>
                                    <a href=" " className="review-link m-r-lg-10"><i
                                        className="fa fa-pencil m-r-lg-5"></i>6
                                        Review </a>
                                    <a href=" " className="review-link m-r-lg-10"><i
                                        className="fa fa-pencil m-r-lg-5"></i>Write
                                        a review</a>
                                    <p className="price p-t-lg-20 p-b-lg-10 f-30 f-bold color-red">{price}</p>
                                    <p className="price-old f-20 color1-5">$1,280.00 - do discount</p>
                                    <p>{serial}</p>
                                    <hr/>
                                    <p><b>Brand :</b>{brand}</p>
                                    <p><b>Code :</b>{code}</p>
                                    <p><b>Manufactor :</b>{manufacturer} </p>
                                    <p><b>Availability :</b><strong
                                        className="color-green color1-green">Instock</strong>
                                    </p>
                                    <hr/>
                                    <div className="pull-left">
                                        <b className="m-r-lg-5">Qty : </b>
                                        <input id="quantity" type="text" className="form-item input-qtl"
                                               defaultValue="1"
                                               ref={(input) => this.textInput = input}/>
                                    </div>
                                    <a className="ht-btn ht-btn-default" onClick={() => {
                                        this.props.addToBasket(this.props.selectedProduct, parseInt(this.textInput.value, 10));
                                    }}>Add to cart</a>
                                    <a href=" " className="ht-btn bg-gray-c bg1-gray-4"><i
                                        className="fa fa-heart-o"></i></a>
                                    <a href=" " className="ht-btn bg-gray-c bg1-gray-4"><i className="fa fa-signal"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="product-description m-b-lg-50">
                        <ul className="nav nav-tabs ht-tabs text-left p-l-lg-30" role="tablist">
                            <li role="presentation" className="active"><a href=" home" aria-controls="home" role="tab"
                                                                          data-toggle="tab">Description</a></li>
                        </ul>
                        <div className="tab-content bg-gray-fa bg1-gray-15 p-lg-30 p-xs-15">
                            <div role="tabpanel" className="tab-pane active" id="home">
                                <div className="txt">
                                    {description}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="product product-grid car m-b-lg-15">
                        <div className="heading">
                            <h3>RELATED PRODUCTS</h3>
                        </div>
                        <div className="row">
                            <div className="col-sm-6 col-md-4 col-lg-4">
                                {
                                    distinctProducts && distinctProducts.map((product, key) => {
                                        return (
                                            <div className="product-item hover-img">
                                                <a href=" " className="product-img"><img
                                                    src={product.image} alt=""/></a>
                                                <div className="product-caption">
                                                    <h4 className="product-name"><a href=" ">{name}</a></h4>
                                                    <ul className="rating">
                                                        <li className="active"><i className="fa fa-star"></i></li>
                                                        <li className="active"><i className="fa fa-star"></i></li>
                                                        <li className="active"><i className="fa fa-star"></i></li>
                                                        <li><i className="fa fa-star"></i></li>
                                                        <li><i className="fa fa-star"></i></li>
                                                    </ul>
                                                    <div className="product-price-group">
                                                        <span className="product-price">${product.price}</span>
                                                    </div>
                                                    <a
                                                        className="ht-btn ht-btn-default"
                                                        onClick={() => {
                                                            this.props.addToBasket(this.props.currentProduct, 1);
                                                        }}
                                                    >
                                                        Add to cart
                                                    </a>
                                                    <ul className="absolute-caption">
                                                        <li><i className="fa fa-heart-o"></i></li>
                                                        <li><i className="fa fa-signal"></i></li>
                                                        <li><i className="fa fa-search"></i></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        );
                                    })
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
};

function mapStateToProps({currentProduct, products}) {
    return {products, currentProduct};
}

export default connect(mapStateToProps, {addToBasket, fetchSingleProduct, whoAmI, fetchProducts})(ProductDetails);
