package test.panel;

//Este es un test que prueba interacciones entre paneles.

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.paneles.types.BonusPanel;
import main.paneles.types.BossPanel;
import main.paneles.types.DrawPanel;
import main.paneles.types.DropPanel;
import main.paneles.types.EncounterPanel;
import main.paneles.types.HomePanel;
import main.paneles.types.NeutralPanel;
import main.personaje.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Esta es una clase que prueba interacciones entre paneles, es decir, next panel y equals.
//las características propias para cada panel es probado en su respectivo archivo.


/**
 * Class for test interaction within panels.
 *
 */
public class InteractionPanelsTest {
  private static final String PLAYER_NAME = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;
  private HomePanel homePanel;
  private NeutralPanel neutralPanel;
  private BonusPanel bonusPanel;
  private DropPanel dropPanel;
  private EncounterPanel encounterPanel;
  private BossPanel bossPanel;
  private DrawPanel drawPanel;
  private Player suguri1;
  private Player suguri2;


  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri1 = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    bossPanel = new BossPanel();
    drawPanel = new DrawPanel();
    neutralPanel = new NeutralPanel();
    bonusPanel = new BonusPanel();
    dropPanel = new DropPanel();
    homePanel = new HomePanel(suguri1);
    encounterPanel = new EncounterPanel();
  }

  @Test
  public void nextPanelTest() {
    var b1 = new BossPanel();
    var d1 = new DrawPanel();
    //Añade siguiente. Debe aumentar.
    bonusPanel.addNextPanel(b1);
    assertEquals(1, bonusPanel.getNextPanels().size());
    //Añadimos segundo. Debe aumentar
    bonusPanel.addNextPanel(d1);
    assertEquals(2, bonusPanel.getNextPanels().size());
    //Añadimos otro, pero es igual al anterior así que no aumenta tamaño.
    bonusPanel.addNextPanel(d1);
    assertEquals(2, bonusPanel.getNextPanels().size());
    //Se intenta agregar otra igual en atributos pero diferente en memoria. No debe aumentar
    var d2 = new DrawPanel();
    bonusPanel.addNextPanel(d2);
    assertEquals(2, bonusPanel.getNextPanels().size());
    //Se cambia un atributo de D2 para que ahora sea diferente a D1. Se agrega y si debe aumentar.
    d2.addPlayer(suguri1);
    bonusPanel.addNextPanel(d2);
    assertEquals(3, bonusPanel.getNextPanels().size());
  }

  @Test
  public void equalsTest() {
    var b1 = new BossPanel();
    var b2 = new BossPanel();

    //veo que sea true el equals
    assertTrue(bonusPanel.equals(bonusPanel));

    assertTrue(bossPanel.equals(b1));
    assertTrue(b1.equals(b2));

    var e1 = new EncounterPanel();
    var e2 = new EncounterPanel();
    assertTrue(encounterPanel.equals(e1));
    assertTrue(e1.equals(e2));

    var h1 = new HomePanel(suguri1);
    var h2 = new HomePanel(suguri1);
    assertTrue(homePanel.equals(h1));
    assertTrue(h2.equals(h1));

    //Acá tienen que ser falsos
    b2.addNextPanel(e2);
    h1.addNextPanel(b1);

    assertFalse(h2.equals(h1));
    assertFalse(b2.equals(b1));
  }
}
