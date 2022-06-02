export default function Price(props) {
    return (<div>
            {props.children}
            <span className={props.product ? "product-price": "f-18"}> ${(Math.round(props.price*100)/100).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</span>
        </div>
    )
}
