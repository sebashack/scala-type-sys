package button.click.observer

import button.subject.observer.ButtonSubjectObserver._
import scala.collection.mutable.{ HashMap }

class ButtonClickObserver extends ButtonObserver {
  val clicks = new HashMap[String, Int]()

  def receiveUpdate(button: ObservableButton) = {
    val count = clicks.getOrElse(button.label, 0) + 1
    clicks.update(button.label, count)
  }
}
