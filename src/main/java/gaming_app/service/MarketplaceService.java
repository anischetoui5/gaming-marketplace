package gaming_app.service;


import gaming_app.model.*;
import gaming_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MarketplaceService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CoinPackageRepository coinPackageRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private InventoryRepository inventoryRepository;

    public Transaction purchaseCoins(Long userId, Long coinPackageId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        CoinPackage coinPackage = coinPackageRepository.findById(coinPackageId)
                .orElseThrow(() -> new RuntimeException("Coin package not found"));
        
        if (!coinPackage.getIsActive()) {
            throw new RuntimeException("Coin package is not available");
        }

        Double newBalance = user.getCoinBalance() + coinPackage.getCoinAmount();
        user.setCoinBalance(newBalance);
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(Transaction.TransactionType.COIN_PURCHASE);
        transaction.setItemId(coinPackageId);
        transaction.setItemType(Transaction.ItemType.COIN_PACKAGE);
        transaction.setAmount(coinPackage.getPrice());
        transaction.setCoinChange((double) coinPackage.getCoinAmount());
        transaction.setNewBalance(newBalance);
        
        return transactionRepository.save(transaction);
    }

    public Transaction purchaseGame(Long userId, Long gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        if (inventoryRepository.existsByUserIdAndGameId(userId, gameId)) {
            throw new RuntimeException("User already owns this game");
        }

        if (user.getCoinBalance() < game.getBasePrice()) {
            throw new RuntimeException("Insufficient coins");
        }

        Double newBalance = user.getCoinBalance() - game.getBasePrice();
        user.setCoinBalance(newBalance);
        userRepository.save(user);

        Inventory inventory = new Inventory();
        inventory.setUser(user);
        inventory.setGame(game);
        inventoryRepository.save(inventory);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(Transaction.TransactionType.GAME_PURCHASE);
        transaction.setItemId(gameId);
        transaction.setItemType(Transaction.ItemType.GAME);
        transaction.setAmount(game.getBasePrice());
        transaction.setCoinChange(-game.getBasePrice());
        transaction.setNewBalance(newBalance);
        
        return transactionRepository.save(transaction);
    }

    public Transaction purchaseProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventoryRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new RuntimeException("User already owns this product");
        }

        if (user.getCoinBalance() < product.getPrice()) {
            throw new RuntimeException("Insufficient coins");
        }

        Double newBalance = user.getCoinBalance() - product.getPrice();
        user.setCoinBalance(newBalance);
        userRepository.save(user);

        Inventory inventory = new Inventory();
        inventory.setUser(user);
        inventory.setProduct(product);
        inventoryRepository.save(inventory);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(Transaction.TransactionType.PRODUCT_PURCHASE);
        transaction.setItemId(productId);
        transaction.setItemType(Transaction.ItemType.PRODUCT);
        transaction.setAmount(product.getPrice());
        transaction.setCoinChange(-product.getPrice());
        transaction.setNewBalance(newBalance);
        
        return transactionRepository.save(transaction);
    }

    public List<Inventory> getUserInventory(Long userId) {
        return inventoryRepository.findByUserId(userId);
    }

    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUserIdOrderByTransactionDateDesc(userId);
    }
}
