import { useEffect, useState, useRef ,useContext} from 'react';
import {Context} from '../../contexts/Context';
import MediaService from '../../services/MediaService';
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
import {useHistory} from 'react-router-dom';
const CreateMedia = () => {
    const history = useHistory();
    const ref = useRef();
    const [media, setMedia] = useState({ selectedFile: '', imageList: [],renderagain:'' });
    const [loading, setLoading] = useState(true);
    const { selectedFile, imageList,renderagain } = media;
    const{users,authorizeControl}=useContext(Context);
    const onImageChange = (e) => {
        setMedia({ ...media, selectedFile: e.target.files[0] })
    }

    const onFileUpload = async () => {
        if (!selectedFile) {
            alert("choose a file...!");
            return;
        }
        const data = new FormData();
        data.append('multipartFile', selectedFile)
        data.append('imageName', selectedFile.name)
        await MediaService.addMedia(data,users).then(res => {
            if (res.status = 200) {
                ref.current.value = ""
                setMedia({ ...media, selectedFile: null })
            }
        });
        setLoading(false)
    }
    async function getAllMedia(){
        const controlContext =await authorizeControl(); 
        if(await controlContext===false){
            history.push('/');
        }
        MediaService.getAllMedia(users).then((res) => {
            setMedia({ ...media, imageList: res.data })
            setLoading(false)
        })
    }

    useEffect(() => {
        console.log("girdi");
        setMedia({ ...media, loading: false })
        getAllMedia();
    }, [selectedFile])

    return (
        <div>
            {loading == true ?
                <Loading /> :
                <div>
                    <div className="container">
                        <HeaderComponent />
                        <div className="col-sm-12 mt-4">
                            <div className="row ">
                                <div className="card col-sm-4 " style={{ backgroundColor: "#a6dced" }}>
                                    <div className="card-header bg-white text-center" style={{ borderRadius: "10px" }}>Add Media File</div>
                                    <div className="card-body">
                                        <input type="file" name="file" ref={ref} className="ml-5" style={{ paddingTop: 20 }} onChange={(e) => onImageChange(e)} />

                                    </div>
                                    <div className="card-body ml-5" style={{ height: "30rem", overflow: "auto" }}>
                                        <button className="btn btn-success" style={{ borderRadius: "5px", marginTop: "23rem" }} onClick={() => onFileUpload()}> Upload Media File</button>
                                    </div>
                                </div>
                                <div className="card col-sm-8" style={{ height: "39rem", overflow: "auto" }} >
                                    <div className="card-header text-center">Media Files</div>
                                    <div className="row ">

                                        {
                                            imageList.map(
                                                (image) => (
                                                    <div className="card-body col-sm-3 ml-4">
                                                        <img src={'data:image/png;base64,' + image.fileContent} width="150rem" height="130rem" style={{ margin: 10 }} />
                                                    </div>
                                                ))
                                        }

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            }
        </div>
    )

}
export default CreateMedia;