package gaming_app.controller;


import gaming_app.model.Inventory;
import gaming_app.model.Transaction;
import gaming_app.service.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {
    
    @Autowired
    private MarketplaceService marketplaceService;

    @PostMapping("/purchase-coins")
    public ResponseEntity<Transaction> purchaseCoins(
            @RequestParam Long userId, 
            @RequestParam Long coinPackageId) {
        try {
            Transaction transaction = marketplaceService.purchaseCoins(userId, coinPackageId);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/purchase-game")
    public ResponseEntity<Transaction> purchaseGame(
            @RequestParam Long userId, 
            @RequestParam Long gameId) {
        try {
            Transaction transaction = marketplaceService.purchaseGame(userId, gameId);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/purchase-product")
    public ResponseEntity<Transaction> purchaseProduct(
            @RequestParam Long userId, 
            @RequestParam Long productId) {
        try {
            Transaction transaction = marketplaceService.purchaseProduct(userId, productId);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/inventory/{userId}")
    public List<Inventory> getUserInventory(@PathVariable Long userId) {
        return marketplaceService.getUserInventory(userId);
    }

    @GetMapping("/transactions/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return marketplaceService.getUserTransactions(userId);
    }
}
