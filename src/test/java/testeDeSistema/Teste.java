package testeDeSistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.*;

public class Teste {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
//		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	  public void ct01CadastroDeLivroComSucesso() {
		//dado que o livro não esta cadastrado
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	    espera();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    //Quando o usuario cadastrar um livro
	    driver.findElement(By.id("isbn")).click();
	    driver.findElement(By.id("isbn")).sendKeys("6523");
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Nome Autor");
	    driver.findElement(By.id("titulo")).click();
	    driver.findElement(By.id("titulo")).sendKeys("Titulo de Livro");
	    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	    //entao apresenta as informacoes do livro
	    assertTrue(driver.getPageSource().contains("Titulo de Livro"));
	  }
	
	@Test
	public void ct02CadastroDeLivroSemSucesso() {
		//dado que o livro já esta cadastrado
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		espera();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		//Quando o usuario cadastrar um livro
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("6523");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Nome Autor");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).sendKeys("Titulo de Livro");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		//entao apresenta as informacoes do livro
		assertTrue(driver.getPageSource().contains("Livro ja cadastrado"));
	}
	
	@Test
	  public void ct03AtualizaLivroComSucesso() {
		//dado que o livro já está cadastrado
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	    espera();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    espera();
	    //Quando o usuario atualizar um livro
	    driver.findElement(By.cssSelector("tr:nth-child(4) .btn-primary")).click();
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Nome Autor Teste");
	    driver.findElement(By.cssSelector(".btn")).click();
	    //entao apresenta as informacoes do livro
	    assertTrue(driver.getPageSource().contains("Nome Autor Teste"));
	  }
	@Test
	public void ct04AtualizaLivroSemSucesso() {
		//dado que o livro já está cadastrado
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		espera();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		driver.findElement(By.linkText("Lista de Livros")).click();
		espera();
		//Quando o usuario atualizar um livro
		driver.findElement(By.cssSelector("tr:nth-child(4) .btn-primary")).click();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.cssSelector(".btn")).click();
		//entao apresenta a mensagem de erro
		assertTrue(driver.getPageSource().contains("Erro não esperado"));
	}
	
	@Test
	  public void ct05ConsultaLivro() {
		//dado que o livro está cadastrado
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		espera();
	    driver.findElement(By.linkText("Livros")).click();
	    //quando o usuario consulta os livros
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    espera();
	    driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(3)")).click();
	    //entao apresenta as informacoes do livro
	    assertTrue(driver.getPageSource().contains("Titulo de Livro"));
	  }
	@Test
	public void ct06ExcluirLivro() {
		//dado que o livro está cadastrado
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		espera();
		driver.findElement(By.linkText("Livros")).click();
		//quando o usuario consulta os livros
		driver.findElement(By.linkText("Lista de Livros")).click();
		espera();
		driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(4)")).click();
		//entao apresenta as informacoes do livro
		assertFalse(driver.getPageSource().contains("Titulo de Livro"));
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}