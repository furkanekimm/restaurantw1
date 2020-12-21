import { useEffect, useRef, useState ,useContext} from "react";
import {Context} from '../../contexts/Context';
import { useHistory } from 'react-router-dom';
import MediaService from "../../services/MediaService";
import ProductService from "../../services/ProductService";
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";


const EditProduct = (props) => {
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const refImage = useRef();
    const{users}=useContext(Context);
    const [product, setProduct] = useState({
        id: history.location.state?.id,
        productName: '',
        description: '',
        select: '',
        multiCategories: [],
        price: '',
        cateid: 0,
        fileContent: '',
        image: ''
    })

    const [category, setCategory] = useState([])

    const [media, setMedia] = useState([])

    const { id, productName, description, select, multiCategories, price, cateid, image, fileContent } = product;

    async function getProductById() {
        const res = await ProductService.getProductById(id,users);
        setProduct({
            ...product,
            productName: res.data.productName,
            description: res.data.description,
            price: res.data.price,
            multiCategories: res.data.categoryId,
            fileContent: res.data.media.fileContent,
            image: res.data.media.id
        })
    }

    async function getAllCategories() {
        const res = await ProductService.listCategories(users);
        await setCategory(res.data);
    }
    async function getAllMedia() {
        const res = await MediaService.getAllMedia(users);
        await setMedia(res.data);
        await setProduct({ ...product, fileContent: res.data[0].fileContent })
        
    }

    useEffect(() => {
        async function getData() {
            await getAllMedia();
            getAllCategories();
            await getProductById();
            setLoading(false)
        }
        getData();
    }, [])

    const changeHandler = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value })
    }

    const updateProduct = async (e) => {
        e.preventDefault();
        let product = {
            id: id,
            productName: productName,
            description: description,
            price: price,
            categoryId: multiCategories,
            media: media.filter(media => media.id == refImage.current.value)[0]
        };
        const res = await ProductService.updateProduct(product,users);
        if (res.status = '200') {
            history.push('/products');
        }
    }

    const cancel = (e) => {
        history.push('/products')
    }

    const changeMultiCate = (id) => {
        if (multiCategories.includes(id) !== true) {
            multiCategories.push(id);
        } else {
            for (let i = 0; i < multiCategories.length; i++) {
                if (id === multiCategories[i]) {
                    multiCategories.splice(i, 1);
                }
            }
        }
    }

    const showImage = async (e) => {
        await setProduct({ ...product, fileContent: media.filter(media => media.id == refImage.current.value)[0].fileContent })
        e.preventDefault();
    }

    const getSelectedCategories = () => {
        const selected = [];
        for (let i = 0; i < category.length; i++) {
            if (multiCategories.includes(category[i].id)) {
                selected.push(<div className="row col-md -12" key={category[i].id}><label className="col-md-12"  ><input type="checkbox" defaultChecked="true"
                    onClick={(e) => changeMultiCate(category[i].id)} />{category[i].name}
                </label></div>)
            } else {
                selected.push(<div className="row col-md -12" key={category[i].id}><label className="col-md-12"><input type="checkbox"
                    onClick={(e) => changeMultiCate(category[i].id)} />{category[i].name}
                </label></div>)
            }

        }
        return selected;
    }

    return (
        <>
            {loading == true ?
                <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Add Product</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Category </label>
                                            <div className="checkbox" style={{ height: "4rem", overflow: "auto" }}>
                                                {getSelectedCategories()}
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <label> Product Name </label>
                                            <input placeholder="Product Name" name="productName" className="form-control"
                                                value={productName} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="form-group">
                                            <label> Description </label>
                                            <input placeholder="Description" name="description" className="form-control"
                                                value={description} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="form-group">
                                            <label> Price </label>
                                            <input placeholder="Price" name="price" className="form-control"
                                                value={price} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="form-group ">
                                            <label> Category Image </label>
                                            <div className="row">
                                                <div className="col-md-6">
                                                    <select onChange={(e) => showImage(e)}
                                                        ref={refImage} className="form-control" id="option">
                                                        {
                                                            media.map(
                                                                media =>
                                                                    <option key={media.id} selected={product.image == media.id}
                                                                        value={media.id}>{media.name} </option>
                                                            )
                                                        }
                                                    </select>
                                                </div>
                                                <div className="col-md-4 offset-md-2">

                                                    <div className="">
                                                        <img src={'data:image/png;base64,' + fileContent}
                                                            width="45rem" height="39rem"
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button className="btn btn-success" onClick={(e) => updateProduct(e)}>Save</button>
                                        <button className="btn btn-danger" onClick={(e) => cancel(e)}
                                            style={{ marginLeft: "10px" }}>Cancel
                                    </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
        </>
    )
}
export default EditProduct;