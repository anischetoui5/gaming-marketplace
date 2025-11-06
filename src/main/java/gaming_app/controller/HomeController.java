package gaming_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // This serves the Thymeleaf template from templates folder
    @GetMapping("/")
    public String home() {
        return "index"; // Looks for src/main/resources/templates/index.html
    }

    // This provides a JSON/text API response
    @GetMapping("/api")
    @ResponseBody
    public String apiInfo() {
        return "Welcome to the Game Marketplace API! \n\n" +
               "Application: Gaming Marketplace 'GameHub'\n\n" +
               "Entités: User, Game, Product, CoinPackage, Transaction, Inventory\n\n" +
               "Relations:\n" +
               "- Un User peut avoir plusieurs Transactions et plusieurs Inventory items\n" +
               "- Un Game peut avoir plusieurs Products\n" +
               "- Un Product appartient à un Game\n" +
               "- Une Transaction est liée à un User\n" +
               "- Un Inventory item est lié à un User et un Game ou Product\n\n" +
               "Endpoints disponibles:\n" +
               "GET  /api/users - Obtenir tous les utilisateurs\n" +
               "POST /api/users - Créer un nouvel utilisateur\n" +
               "GET  /api/games - Obtenir tous les jeux\n" +
               "POST /api/games - Créer un nouveau jeu\n" +
               "POST /api/marketplace/purchase-coins - Acheter des coins\n" +
               "POST /api/marketplace/purchase-game - Acheter un jeu\n" +
               "POST /api/marketplace/purchase-product - Acheter un produit\n" +
               "GET  /api/marketplace/inventory/{userId} - Obtenir l'inventaire d'un utilisateur\n" +
               "GET  /api/marketplace/transactions/{userId} - Obtenir l'historique des transactions";
    }
}