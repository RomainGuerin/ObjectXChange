<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 27/02/2025"
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html data-theme="emerald">
<head>
  <title>ObjectXChange</title>
  <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.24/dist/full.min.css" rel="stylesheet" type="text/css" />
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<div class="navbar bg-base-100 mb-8">
  <div class="flex-1">
    <a class="btn btn-ghost text-xl">Object<span class="text-primary">X</span>Change</a>
  </div>
  <div class="flex-none">
    <ul class="menu menu-horizontal px-1">
      <li><button class="btn btn-ghost" onclick="modal_connexion.showModal()">Connexion</button></li>
      <li><button class="ml-2 btn btn-outline" onclick="modal_inscription.showModal()">Inscription</button></li>
    </ul>
  </div>
</div>

<div class="mx-6">
  <div class="mb-6 flex items-center">
    <div class="font-semibold text-xl">Liste objets</div>
    <select class="ml-4 select select-bordered w-full max-w-xs">
      <option disabled selected>Toutes les catégories</option>
      <option>Catégorie 1</option>
      <option>Catégorie 2</option>
    </select>
    <button onclick="my_modal_4.showModal()" class="ml-auto btn">Ajouter un objet</button>
  </div>

  <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
    <div class="card card-compact bg-base-100 shadow-xl">
      <figure>
        <img src="https://img.daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.webp" alt="Shoes" />
      </figure>
      <div class="card-body">
        <h2 class="card-title">Produit 1</h2>
        <p>If a dog chews shoes whose shoes does he choose?</p>
        <div class="card-actions justify-end mt-4">
          <button class="btn" onclick="my_modal_3.showModal()">Proposer un échange</button>
        </div>
      </div>
    </div>

    <div class="card card-compact bg-base-100 shadow-xl">
      <figure>
        <img src="https://img.daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.webp" alt="Shoes" />
      </figure>
      <div class="card-body">
        <h2 class="card-title">Produit 2</h2>
        <p>If a dog chews shoes whose shoes does he choose?</p>
        <div class="card-actions justify-end">
          <button class="btn" onclick="my_modal_3.showModal()">Proposer un échange</button>
        </div>
      </div>
    </div>

    <div class="card card-compact bg-base-100 shadow-xl">
      <figure>
        <img src="https://img.daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.webp" alt="Shoes" />
      </figure>
      <div class="card-body">
        <h2 class="card-title">Produit 3</h2>
        <p>If a dog chews shoes whose shoes does he choose?</p>
        <div class="card-actions justify-end">
          <button class="btn" onclick="my_modal_3.showModal()">Proposer un échange</button>
        </div>
      </div>
    </div>
  </div>

  <dialog id="my_modal_3" class="modal">
    <div class="modal-box">
      <form method="dialog">
        <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
      </form>
      <h3 class="text-lg font-bold">Hello!</h3>
      <p class="py-4">Press ESC key or click on ✕ button to close</p>
    </div>
  </dialog>

  <dialog id="my_modal_4" class="modal">
    <div class="modal-box w-11/12 max-w-5xl">
      <h3 class="text-lg font-bold">Hello!</h3>
      <p class="py-4">Click the button below to close</p>
      <div class="modal-action">
        <form method="dialog">
          <!-- if there is a button, it will close the modal -->
          <button class="btn">Close</button>
        </form>
      </div>
    </div>
  </dialog>

  <dialog id="modal_connexion" class="modal">
    <div class="modal-box relative">
      <!-- Bouton pour fermer le modal -->
      <button type="button" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2"
              onclick="document.getElementById('modal_connexion').close()">
        ✕
      </button>
      <!-- Formulaire de connexion fonctionnel -->
      <form action="login" method="post">
        <h1 class="font-semibold text-xl mb-4">Connexion</h1>
        <label class="input input-bordered flex items-center gap-2 mb-4">
          <!-- Exemple d'icône -->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path d="M2.5 3A1.5 1.5 0 0 0 1 4.5v.793c.026.009.051.02.076.032L7.674 8.51c.206.1.446.1.652 0l6.598-3.185A.755.755 0 0 1 15 5.293V4.5A1.5 1.5 0 0 0 13.5 3h-11Z" />
            <path d="M15 6.954 8.978 9.86a2.25 2.25 0 0 1-1.956 0L1 6.954V11.5A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5V6.954Z" />
          </svg>
          <input type="text" name="email" class="grow" placeholder="Email" required />
        </label>

        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path fill-rule="evenodd" d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z" clip-rule="evenodd" />
          </svg>
          <input type="password" name="password" class="grow" placeholder="Mot de passe" required />
        </label>
        <button class="btn btn-primary" type="submit">Se connecter</button>
      </form>
    </div>
  </dialog>

  <dialog id="modal_inscription" class="modal">
    <div class="modal-box relative">
      <!-- Bouton pour fermer le modal -->
      <button type="button" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2"
              onclick="document.getElementById('modal_inscription').close()">
        ✕
      </button>
      <form action="inscription" method="post">
        <h1 class="font-semibold text-xl mb-4">Inscription</h1>
        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  class="h-4 w-4 opacity-70">
            <path
                    d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z" />
          </svg>
          <input type="text" class="grow" placeholder="Username" />
        </label>

        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path d="M2.5 3A1.5 1.5 0 0 0 1 4.5v.793c.026.009.051.02.076.032L7.674 8.51c.206.1.446.1.652 0l6.598-3.185A.755.755 0 0 1 15 5.293V4.5A1.5 1.5 0 0 0 13.5 3h-11Z" />
            <path d="M15 6.954 8.978 9.86a2.25 2.25 0 0 1-1.956 0L1 6.954V11.5A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5V6.954Z" />
          </svg>
          <input type="text" name="email" class="grow" placeholder="Email" required />
        </label>

        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path fill-rule="evenodd" d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z" clip-rule="evenodd" />
          </svg>
          <input type="password" name="password" class="grow" placeholder="Mot de passe" required />
        </label>
        <button class="btn btn-primary" type="submit">Inscription</button>
      </form>
    </div>
  </dialog>

</div>
</body>
</html>
