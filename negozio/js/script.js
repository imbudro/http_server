const products = [
    {
      productName: "OXFORD SHIRT TOMMY HILFIGER",
      category: "Shirts",
      price: "30",
      image: "oxford.webp",
      size: ['S', 'M', 'XXL'],
      sale:true,
    },
    {
      productName: "GANT GINGHAM SHIRT",
      category: "Shirts",
      price: "49",
      image: "gant.webp",
      size: ['L'],
      sale:false,
    },
    {
      productName: "LUMBERJACK SHIRT",
      category: "Shirts",
      price: "49",
      image: "lumberjackshirt.webp",
      size: ['S'],
      sale:false,
    },
    {
      productName: "BOSS SLIM FIT JEANS",
      category: "Jeans",
      price: "99",
      image: "boss.webp",
      size: ['44', '52'],
      sale:false,
    },
    {
      productName: "ARMANI POCKETS PANT",
      category: "Jeans",
      price: "129",
      image: "armani.webp",
      size: ['48', '50', '52'],
      sale:false,
    },
    {
      productName: "RALPH LAUREN AERA LACE",
      category: "Shoes",
      price: "129",
      image: "/images/polo.webp",
      size: ['38', '39', '43', '44'],
      sale:true,
    },
    {
      productName: "RALPH LAUREN HERITAGE",
      category: "Shoes",
      price: "110",
      image: "polo2.webp",
      size: ['44'],
      sale:false,
    },
    {
      productName: "CLOSURE JACKET LONDON",
      category: "Jackets",
      price: "189",
      image: "london.webp",
      size: ['L', 'XL'],
      sale:false,
    },
    {
      productName: "JACK JONES LONG PUFFER",
      category: "Jackets",
      price: "77",
      image: "jack.webp",
      size: ['S', 'M', 'L', 'XL'],
      sale:false,
    },
    {
      productName: "POLO RALPH LAUREN ROUND",
      category: "Sunglasses",
      price: "111",
      image: "sunRalph.webp",
      size: [''],
      sale:true,
    },
  ]


const row = document.querySelector("#products-container")


function clothesPrint(prod=products){
  row.innerHTML = ""

  prod.forEach(pr => {
    let sizes = ""
    pr.size.forEach(s => {
      sizes+= `<span class="badge text-bg-dark me-2 fs-6">${s}</span>`
    })
    row.innerHTML+= `
        <div class="col-sm-4 d-flex mt-5">
            <div class="card mx-auto ${pr.sale ? "border border-3 border-danger" : ""}" style="width: 18rem;">
                <img src="./images/${pr.image}" class="card-img-top">
                <div class="card-body">
                  <h5 class="card-title">${pr.productName}</h5>
                  <p class="card-text">
                    <span class="fw-bold">${sizes}</span>

                    <h4>Price: ${pr.price}€</h4>

                    <h5 class="fw-bold text-danger ${!pr.sale ? "d-none" : ""}">On Sale</h5>
                  </p>
                </div>
            </div>
        </div>`

        
})
}



const cerca = document.querySelector("#ricerca")

cerca.addEventListener("input", e => {
  let prod = []

  products.forEach(p => {
    if(p.productName.toLowerCase().startsWith(e.target.value.toLowerCase())){
      prod.push(p)
    }
  })

  clothesPrint(prod)
});



const options = document.querySelectorAll(".filter")

options.forEach(opt => {
  opt.addEventListener("click", e => {
    
    let c = e.target.getAttribute("category").toLowerCase()
    
    if(c == "all"){
      clothesPrint()
    }
    
    
    else{
      let prod = []

      products.forEach(p => {
        if(p.category.toLowerCase() == c){
          prod.push(p)
        }
      })

      clothesPrint(prod)
    }
    
  
  })
})


clothesPrint()

