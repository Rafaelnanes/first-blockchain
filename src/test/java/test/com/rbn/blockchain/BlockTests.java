package test.com.rbn.blockchain;

import com.rbn.blockchain.model.Block;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTests {

  @Test
  @DisplayName("has a timestamp, lastHash, hash, and data property")
  void simpleCreation() {
    var block = Block.mine("lastHash", "data");
    assertTrue(block.getTimestamp() > 0);
    assertNotNull(block.getHash());
    assertEquals("lastHash", block.getLastHash());
    assertEquals(2, block.getNonce());
    assertEquals("data", block.getData());
    assertEquals(4, block.getDifficulty());
  }

  @Test
  @DisplayName("getGenesisBlock()")
  void getGenesisBlock() {
    var genesisBlock = Block.getGenesisBlock();
    assertTrue(genesisBlock.getTimestamp() > 0);
    assertEquals("d2b402d8ef34562e8c1391dd5cf0a0da1e902642a23965440953bbe4762b474e", genesisBlock.getHash());
    assertEquals("lastGenesisHash", genesisBlock.getLastHash());
    assertEquals(-1, genesisBlock.getNonce());
    assertEquals("genesisData", genesisBlock.getData());
    assertEquals(-1, genesisBlock.getDifficulty());
  }

  @Test
  @DisplayName("mineBlock()")
  void mineBlock() {
    var genesisBlock = Block.getGenesisBlock();
    var block = Block.mine(genesisBlock.getHash(), "data");
    String generatedHash = Block.generateHash(genesisBlock.getHash(),
        "data",
        block.getNonce(),
        block.getDifficulty(),
        block.getEffortTime(),
        block.getTimestamp());
    assertEquals(generatedHash, block.getHash());
    assertTrue(block.getEffortTime() > 0);
  }

}
