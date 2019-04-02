package io.github.sullis.aws.codedeploy

import org.scalatest.WordSpec
import org.scalatest.Matchers
import software.amazon.awssdk.services.codedeploy.CodeDeployAsyncClient
import software.amazon.awssdk.services.codedeploy.model._
import scala.compat.java8.FutureConverters.toScala
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global

class DeploymentHistoryTest
  extends WordSpec with Matchers {

  private val client = CodeDeployAsyncClient.create

  "DeploymentHistory" should {

    "fetch" in {
      val deployIds = client.listDeployments().get.deployments
      val deploymentsResp = client.batchGetDeployments(BatchGetDeploymentsRequest.builder.deploymentIds(deployIds).build).get
      for {
        app <- deploymentsResp.deploymentsInfo.asScala.map(di => di.applicationName -> di).groupBy(_._1).mapValues(_.map(_._2))
      } yield {
        val appName = app._1
        val deployments = app._2
        System.out.println(s"${appName}: deployCount=${deployments.size}")
      }
    }
  }

}
