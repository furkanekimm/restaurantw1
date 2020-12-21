import { useEffect ,useRef,useState,useContext} from "react";
import {Context} from '../../contexts/Context';
import {useHistory} from 'react-router-dom';
import MediaService from "../../services/MediaService";
import ProductService from "../../services/ProductService";
import HeaderComponent from '../HeaderComponent';

const AddProduct=(props)=>{
    const{users}=useContext(Context);
    const history = useHistory();
    const refImage = useRef();

    const[product,setProduct] = useState({
        productName:'',
        description:'',
        select:'',
        multiCategories:[],
        price:'',
        cateid:0,
        selectedImage:'',
        image:''
    })

    const[category,setCategory] = useState([])

    const[media,setMedia] = useState([])
    
    const{productName,description,select,multiCategories,price,cateid,image,selectedImage} = product;

    async function getAllCategories(){
        const res = await ProductService.listCategories(users);
        await setCategory(res.data);
    }
    async function getAllMedia(){
        const res = await MediaService.getAllMedia(users);
        await setMedia(res.data);
        await setProduct({...product,selectedImage:res.data[0]})
    }
    
    useEffect(()=>{
        getAllMedia();
        getAllCategories();
    },[])

    const changeHandler=(e)=>{
        setProduct({...product,[e.target.name]:e.target.value})
    }

    const saveProduct=async (e)=>{
        e.preventDefault();
        let product = {
            productName: productName,
            description: description,
            price: price,
            categoryId: multiCategories,
            media:selectedImage
        };
        const res = await ProductService.addProduct(product,users);
        if(res.status='200'){
            history.push('/products')
        }
    }

    const cancel = (e)=>{
        history.push('/products')
    }

    const changeMultiCate=(id)=>{
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

    const showImage = async (e)=>{
        await setProduct({...product,selectedImage:media.filter(media =>media.id==refImage.current.value)[0]})
        e.preventDefault();
    }
  

    return(
 <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Product</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Category </label>
                                        <div className="checkbox" style={{height: "4rem", overflow: "auto"}}>
                                            {
                                                category.map(   
                                                    category =>
                                                        <div className="row col-md -12" key={category.id}>
                                                            <label><input type="checkbox" value=""
                                                                          onClick={(e) => changeMultiCate(category.id)}/>{category.name}
                                                            </label>
                                                        </div>
                                                )
                                            }
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label> Product Name </label>
                                        <input placeholder="Product Name" name="productName" className="form-control"
                                               value={productName} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Description </label>
                                        <input placeholder="Description" name="description" className="form-control"
                                               value={description} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Price </label>
                                        <input placeholder="Price" name="price" className="form-control"
                                               value={price} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <div className="form-group ">
                                        <label> Category Image </label>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <select onChange={(e) => showImage(e)}
                                                    ref={refImage}    className="form-control" id="option">
                                                    {
                                                        media.map(
                                                            media =>
                                                                <option key={media.id}
                                                                        value={media.id}>{media.name} </option>
                                                        )
                                                    }
                                                </select>
                                            </div>
                                            <div className="col-md-4 offset-md-2">

                                                            <div className="">
                                                                <img src={'data:image/png;base64,' + selectedImage.fileContent}
                                                                     width="45rem" height="39rem"
                                                                />
                                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                    <button className="btn btn-success" onClick={(e)=>saveProduct(e)}>Save</button>
                                    <button className="btn btn-danger" onClick={(e)=>cancel(e)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    )
}
export default AddProduct;