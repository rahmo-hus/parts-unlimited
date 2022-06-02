import React from 'react';
import {connect} from 'react-redux';
import {deleteCar, fetchCar as fetchSingleCar, fetchCars} from '../../actions/Car'
import {Link} from 'react-router-dom';
import {whoAmI} from "../../actions/Auth";
import {Button, CircularProgress, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Price from "../commons/Price";
import {Carousel} from "react-responsive-carousel";
import {history} from '../../helpers/History'
import "react-responsive-carousel/lib/styles/carousel.min.css";

class CarDetails extends React.Component {

    constructor() {
        super();
        this.state = {
            loading: true,
            dialogOpen: false
        }
    }

    componentDidMount() {
        const id = window.location.href.charAt(window.location.href.length - 1);
        this.props.fetchSingleCar(id).then(() => {
            this.setState({loading: false})
        });
        this.props.fetchCars();
        this.props.whoAmI();
    }

    render() {

        var {
            id,
            name,
            price,
            condition,
            brand,
            images,
            description,
            door,
            body,
            year,
            model,
            fuel,
            transmission,
            color,
            mileage,
            features
        } = this.props.selectedCar;

        let distinctCars = Object.assign(this.props.cars);
        let index = distinctCars.indexOf(distinctCars.find(e => e.id === id));
        if (index !== -1) {
            distinctCars.splice(index, 1);
        }
        distinctCars = distinctCars.slice(0, 3);

        const handleOpen = () => this.setState({dialogOpen: true});
        const handleClose = () => this.setState({dialogOpen: false, deletePartClicked: false});


        if (this.state.loading === false) {
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
                                this.props.deleteCar(id);
                                handleClose();
                                history.push('/cars');
                                window.location.reload();
                            }} autoFocus>
                                Yes
                            </Button>
                        </DialogActions>
                    </Dialog>
                    <div className="hidden-xs">
                        <div className="row">
                            <div className="col-lg-6">
                                <ul className="ht-breadcrumb pull-left">
                                    <li className="home-act"><a><i className="fa fa-home"></i></a></li>
                                    <li className="home-act"><Link to={"/cars"}>Cars</Link></li>

                                    <li className="active">{name}</li>
                                </ul>
                            </div>
                            {this.props.user && this.props.user.roles[0] === 'ROLE_WAREHOUSE' ?
                                <div className="col-lg-6">
                                    <button onClick={handleOpen} style={{width: 25, height: 25}}
                                            className="col-sm-1 col-md-1 col-lg-1 p-lg-0 ht-btn ht-btn-default pull-right">
                                        <i style={{marginLeft: 7}} className="fa fa-trash center"/>
                                    </button>
                                </div> : <></>
                            }
                        </div>
                    </div>
                    <section className="m-t-lg-30 m-t-xs-0">
                        <div className="product_detail no-bg p-lg-0">
                            <h3 className="product-name color1-f">{name} /
                                <span className="product-price color-red">${price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}.00 <i className="color-9 color1-9"> ( Plus Taxes & Licensing ) </i></span>
                            </h3>
                            <div className="row">
                                <div className="col-md-7 col-lg-8">
                                    <div className="product-img-lg bg-gray-f5 bg1-gray-15">
                                        <div className="image-zoom row m-t-lg-5 m-l-lg-ab-5 m-r-lg-ab-5">
                                            <div className="col-md-12 col-lg-12 p-lg-5">
                                                <Carousel showArrows={true}>
                                                    {
                                                        images && images.map((image, key) => {
                                                                return <div>
                                                                    <img src={`${image}`} alt=""/>
                                                                </div>
                                                            }
                                                        )
                                                    }
                                                </Carousel>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-5 col-lg-4">
                                    <ul className="product_para-1 p-lg-15 bg-gray-f5 bg1-gray-15">
                                        <li><span>Make :</span>{brand}</li>
                                        <li><span>Model :</span>{model}</li>
                                        <li><span>Body :</span>{body}</li>
                                        <li><span>Fuel :</span>{fuel}</li>
                                        <li><span>Transmission :</span>{transmission}</li>
                                        <li><span>Color :</span>{color}</li>
                                        <li><span>Door :</span>{door}</li>
                                        <li><span>Mileage :</span>{mileage}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div className="row m-t-lg-30 m-b-lg-50">
                            <div className="col-md-8">
                                <div className="m-b-lg-30">
                                    <div className="heading-1"><h3>Overview</h3></div>
                                    <div className="m-b-lg-30 bg-gray-fa bg1-gray-2 p-lg-30 p-xs-15">
                                        <p className="color1-9">
                                            {description}
                                        </p>
                                    </div>
                                </div>
                                <div className="m-b-lg-30">
                                    <div className="heading-1"><h3>Features & Options</h3></div>
                                    <div className="bg-gray-fa bg1-gray-2 p-lg-30 p-xs-15">
                                        <ul className="list-feature">
                                            {
                                                features && features.map((feature, key) => {
                                                    return <li><i className="fa fa-check"></i>{feature} </li>
                                                })
                                            }
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div className="col-sm-12 col-md-4 col-lg-4">
                                <div className="heading-1">
                                    <h3><i className="fa fa-envelope-o"></i>Send message</h3>
                                </div>
                                <div className="bg-gray-fa bg1-gray-2 p-lg-20">
                                    <form>
                                        <div className="form-group">
                                            <input type="text" className="form-control form-item"
                                                   placeholder="Full name"/>
                                        </div>
                                        <div className="form-group">
                                            <input type="email" className="form-control form-item" placeholder="Email"/>
                                        </div>
                                        <textarea className="form-control form-item h-200 m-b-lg-10"
                                                  placeholder="Content" rows="3"></textarea>
                                        <button type="submit" className="ht-btn ht-btn-default">Send</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div className="product product-grid product-grid-2 car m-b-lg-15">
                            <div className="heading">
                                <h3>Other Cars</h3>
                            </div>
                            <div className="row">
                                {
                                    distinctCars && distinctCars.map((car, key) => {
                                        return <div className="col-sm-12 col-md-6 col-lg-4">
                                            <div className="product-item hover-img">
                                                <Link to={`/cars/singleproduct/${car.id}`} className="product-img">
                                                    <img src={car.images[0]} alt=""/>
                                                </Link>
                                                <div className="product-caption">
                                                    <h4 className="product-name">
                                                        <a href="">{car.name}</a>
                                                        <Price price={car.price}/>
                                                    </h4>
                                                </div>
                                                <ul className="absolute-caption">
                                                    <li><i className="fa fa-clock-o"></i>{car.year}</li>
                                                    <li><i className="fa fa-car"></i>Auto</li>
                                                    <li><i className="fa fa-road"></i>{car.fuel}</li>
                                                </ul>
                                            </div>
                                        </div>
                                    })
                                }
                            </div>
                        </div>
                    </section>
                </div>
            )
        } else {
            return (<div className="wrap-body-inner h-500">
                <div style={{
                    display: "flex",
                    marginTop: 220,
                    justifyContent: "center"
                }}>
                    <CircularProgress/>
                </div>
            </div>)
        }
    }
};

function mapStateToProps({singleCar, authentication, cars}) {

    return {selectedCar: singleCar, user: authentication.user, cars};
}

export default connect(mapStateToProps, {fetchSingleCar, fetchCars, whoAmI, deleteCar})(CarDetails);


