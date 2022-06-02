import React from 'react';
import {connect} from 'react-redux';
import {addToBasket} from '../../actions/Basket';
import {deleteProduct, fetchProducts, fetchSingleProduct} from "../../actions/Product";
import {whoAmI} from "../../actions/Auth";
import {addDiscount, deleteDiscount} from "../../actions/Discount";
import {history} from "../../helpers/History";
import {Button, CircularProgress, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Price from "../commons/Price";

class ProductDetails extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            addToDiscountClicked: false,
            discountStartDate: '',
            discountEndDate: '',
            discountPercentage: 0,
            dialogOpen: false,
            deletePartClicked: false,
            addToBasketClicked: false
        }

        this.addToDiscountClick = this.addToDiscountClick.bind(this);
    }

    componentDidMount() {
        const id = window.location.href.charAt(window.location.href.length - 1);
        this.props.fetchSingleProduct(id).then(() => {
            this.props.fetchProducts(this.props.currentProduct.category);
        });
    }

    addToDiscountClick = (e) => {
        this.setState({addToDiscountClicked: true});
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
            quantity,
            discount,
            cars
        } = this.props.currentProduct !== null ? this.props.currentProduct : {};

        const saveDiscount = () => {
            this.props.addDiscount({
                discountPercentage: this.state.discountPercentage,
                endDate: this.state.discountEndDate,
                startDate: this.state.discountStartDate,
                partId: id
            });
            window.location.reload();
        }

        const user = JSON.parse(localStorage.getItem("user"));
        let distinctProducts = this.props.products && Object.assign(this.props.products);
        if (distinctProducts) {
            let index = distinctProducts.indexOf(distinctProducts.find(e => e.id === id));
            if (index !== -1) {
                distinctProducts.splice(index, 1);
            }
            distinctProducts = distinctProducts.slice(0, 3);
        }


        const handleOpen = () => this.setState({dialogOpen: true});
        const handleClose = () => this.setState({dialogOpen: false, deletePartClicked: false});

        const deleteDiscount = () => {
            this.props.deleteDiscount(discount.id);
            handleClose();
            window.location.reload();
        }

        const addBadge = () => {
            return (
                <span className="badge"/>
            )
        }

        const deletePart = () => {
            this.props.deleteProduct(id);
            handleClose();
            history.push('/products');
            window.location.reload();
        }

        return (
            <div className="wrap-body-inner">
                <Dialog
                    open={this.state.dialogOpen || this.state.deletePartClicked}
                    onClose={handleClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">
                        {"Are you sure?"}
                    </DialogTitle>
                    <DialogContent>
                        <p id="alert-dialog-description">
                            Are you sure you want to remove this item?
                        </p>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>No</Button>
                        <Button onClick={() => {
                            if (this.state.deletePartClicked === true) {
                                deletePart();
                            } else {
                                deleteDiscount();
                            }
                        }} autoFocus>
                            Yes
                        </Button>
                    </DialogActions>
                </Dialog>
                <div className=" col-sm-12 col-md-12 col-lg-12">
                    <div style={{width: 25}}
                         className="pull-right">
                        {user && user.roles[0] === 'ROLE_WAREHOUSE' ?
                            <div className="col-lg-6">
                                <button style={{width: 25, height: 25}}
                                        onClick={() => {
                                            this.setState({deletePartClicked: true})
                                        }}
                                        className="col-sm-1 col-md-1 col-lg-1 p-lg-0 ht-btn ht-btn-default pull-right">
                                    <i style={{marginLeft: 7}} className="fa fa-trash center"/>
                                </button>
                            </div> : <></>
                        }
                    </div>
                    {
                        this.props.currentProduct ?
                            <div>
                                <div
                                    className="product-list product_detail p-lg-30 p-xs-15 bg-gray-fa bg1-gray-15 m-b-lg-50">
                                    <div className="row">
                                        <div className="image-zoom col-md-6 col-lg-6">
                                            <div className="product-img-lg p-lg-10 m-b-xs-30 text-center">
                                                <a>
                                                    {
                                                        image && <img src={image} alt=""/>
                                                    }
                                                </a>
                                            </div>
                                            {
                                                user && user.roles[0] === 'ROLE_SALES' && discount &&
                                                <div className="product_para p-t-lg-10">
                                                    <p className="f-bold">DISCOUNT:
                                                        {discount.active === true ?
                                                            <strong className="color-green ">ACTIVE</strong> :
                                                            <strong className="color-red">INACTIVE</strong>}</p>
                                                    <p className="price"> Percentage: <strong>{discount.discountPercentage}%</strong>
                                                    </p>
                                                    <p>From: <strong>{discount.startDate}</strong> to <strong>{discount.endDate}</strong>
                                                    </p>
                                                    <button className="ht-btn ht-btn-default"
                                                            onClick={handleOpen}>Remove
                                                    </button>
                                                </div>
                                            }
                                        </div>
                                        <div className="col-md-6 col-lg-6">
                                            <h3 className="product-name">{name}</h3>
                                            <div className="product_para">
                                                <p className="price p-t-lg-20 p-b-lg-10 f-30 f-bold color-red"> ${(Math.round(price*100)/100).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</p>
                                                {
                                                    discount && discount.active === true &&
                                                    <p className="price-old f-20 color1-5">${Math.round((100 * price) / (100 - discount.discountPercentage)).toFixed(2)}</p>
                                                }
                                                <p>Serial number: <strong>{serial}</strong></p>
                                                <hr/>
                                                <p><b>Brand :</b>{brand}</p>
                                                <p><b>Code :</b>{code}</p>
                                                <p><b>Manufacturer :</b>{manufacturer}</p>
                                                <p><b>Availability :</b>
                                                    {
                                                        quantity && quantity > 0 ?
                                                            <strong className="color-green color1-green">Instock
                                                                ({quantity} pieces)</strong>
                                                            : <strong className="color-red color1-red">Out of
                                                                stock</strong>
                                                    }
                                                </p>
                                                <hr/>
                                                {

                                                    user && user.roles[0] === 'ROLE_CUSTOMER' &&
                                                    <div className="pull-left">
                                                        <b className="m-r-lg-5">Qty : </b>
                                                        <input id="quantity" type="number"
                                                               className="form-item input-qtl"
                                                               defaultValue="1" max={quantity}
                                                               ref={(input) => this.textInput = input}/>
                                                    </div>
                                                }
                                                {
                                                    user && (user.roles[0] === 'ROLE_CUSTOMER' ?
                                                            <div>
                                                                <button className={this.props.basketProducts !== [] &&
                                                                    this.props.currentProduct.quantity > 0 &&
                                                                    (!this.props.basketProducts.find(p => p.product.id === this.props.currentProduct.id) ||
                                                                    this.props.basketProducts.find(p => p.product.id === this.props.currentProduct.id)
                                                                && this.props.basketProducts.find(p => p.product.id === this.props.currentProduct.id).quantity
                                                                    - this.props.currentProduct.quantity < 0) ? "ht-btn ht-btn-default" : "ht-btn" } onClick={() => {
                                                                    this.setState({addToBasketClicked: true})
                                                                    this.props.addToBasket(this.props.currentProduct, parseInt(this.textInput.value, 10));
                                                                    window.scrollTo({
                                                                        top: 0,
                                                                        behavior: 'smooth'
                                                                    })
                                                                }}>Add to cart
                                                                </button>
                                                            </div>
                                                            : user.roles[0] === 'ROLE_SALES' ?
                                                                this.state.addToDiscountClicked === false &&
                                                                <button className="ht-btn ht-btn-default"
                                                                        onClick={this.addToDiscountClick}>Add
                                                                    discount</button> : <></>
                                                    )
                                                }
                                                {
                                                    this.state.addToDiscountClicked &&
                                                    <div className="product_para">
                                                        <b className="m-r-lg-5"> Percentage (%): </b>
                                                        <input id="discount" type="text" className="form-item input-qtl"
                                                               defaultValue="0" onChange={(e) => {
                                                            this.setState({discountPercentage: e.target.value})
                                                        }
                                                        }/>
                                                        <br/>
                                                        <b className="m-r-lg-5"> Start date: </b>
                                                        <input type="date" className="form-item input-qtl m-t-lg-5"
                                                               style={{width: 150}} onChange={(e) => {
                                                            this.setState({discountStartDate: e.target.value})
                                                        }
                                                        }/>
                                                        <br/>
                                                        <b className="m-r-lg-5 p-r-lg-10"> End date: </b>
                                                        <input type="date" className="form-item input-qtl m-t-lg-5"
                                                               pattern="dd-mm-yyyy"
                                                               style={{width: 150}} onChange={(e) => {
                                                            this.setState({discountEndDate: e.target.value})
                                                        }
                                                        }/>
                                                        <br/>
                                                        <button className="ht-btn ht-btn-default m-t-lg-5"
                                                                onClick={saveDiscount}>Save discount
                                                        </button>
                                                    </div>
                                                }
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="product-description m-b-lg-50">
                                    <ul className="nav nav-tabs ht-tabs text-left p-l-lg-30" role="tablist">
                                        <li role="presentation" className="active"><a href=" home" aria-controls="home"
                                                                                      role="tab"
                                                                                      data-toggle="tab">Description</a>
                                        </li>
                                    </ul>
                                    <div className="tab-content bg-gray-fa bg1-gray-15 p-lg-30 p-xs-15">
                                        <div role="tabpanel" className="tab-pane active" id="home">
                                            <div className="txt">
                                                {description}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            : <div
                                style={{display: 'flex', justifyContent: 'center', paddingTop: 150, paddingBottom: 150}}>
                                <CircularProgress/>
                            </div>
                    }
                    <div className="product product-grid car m-b-lg-15">
                        <div className="heading">
                            <h3>RELATED PRODUCTS</h3>
                        </div>
                        <div className="row">
                            {
                                distinctProducts && distinctProducts.map((product, key) => {
                                    return (
                                        <div className="col-sm-6 col-md-4 col-lg-4">
                                            <div className="product-item hover-img">
                                                <a href={`/products/singleproduct/${product.id}`} className="product-img"><img
                                                    src={product.image} alt=""/></a>
                                                <div className="product-caption">
                                                    <h4 className="product-name"><a href=" ">{product.name}</a></h4>
                                                    <div className="product-price-group">
                                                        <span className="product-price">${product.price}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    );
                                })
                            }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
};

function mapStateToProps({currentProduct, products, basketProducts}) {
    return {products, currentProduct, basketProducts};
}

export default connect(mapStateToProps, {
    addToBasket,
    addDiscount,
    fetchSingleProduct,
    whoAmI,
    fetchProducts,
    deleteDiscount,
    deleteProduct
})(ProductDetails);
